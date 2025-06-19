package com.saga.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.saga.dto.request.EmailNotify;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Service class responsible for sending emails using templates and tracking notification history.
 * Implements email sending functionality with Thymeleaf template processing and Kafka integration
 * for notification tracking.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Processes and sends an email based on the provided email notification details.
     * Uses Thymeleaf template engine for processing email templates and sends the email
     * using JavaMailSender. Records the notification history through Kafka.
     *
     * @param emailNotify the email notification object containing all necessary information
     *                    including recipient, subject, template location, and parameters
     */
    public void execute(EmailNotify emailNotify) {
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = "";
        try {
            payload = objectMapper.writeValueAsString(emailNotify);
            log.info("start send mail --------------");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(emailNotify.getParameterMap());
            String body = templateEngine.process(emailNotify.getTemplateLocation(), context);
            helper.setTo(emailNotify.getTo());
            helper.setSubject(emailNotify.getSubject());
            helper.setFrom(emailNotify.getFrom());
            helper.setText(body, emailNotify.isHtml());
            javaMailSender.send(message);
            createNotifyHistory("", payload, "Thành Công");
            log.info("end send mail success--------------");
        } catch (Exception e) {
            log.error("end send mail error-------------- {}", e.getMessage());
            createNotifyHistory(e.getMessage(), payload, "Thất Bại");
        }
    }

    /**
     * Creates and publishes a notification history record to Kafka.
     *
     * @param errors     error message if any occurred during email sending
     * @param pushNotify the original notification payload
     * @param status     the status of the email sending operation ("Thành Công" or "Thất Bại")
     */
    private void createNotifyHistory(String errors, String pushNotify, String status) {
        Map<String, Object> notifyHistory = Map.of("errors", errors, "pushNotify", pushNotify, "status", status);
        kafkaTemplate.send("notify", new Gson().toJson(notifyHistory));
    }
}
