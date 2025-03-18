package com.saga.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.dto.request.EmailNotify;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

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
            String body = templateEngine.process("VERIFY_OTP_REGISTER", context);
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

    private void createNotifyHistory(String errors, String pushNotify, String status) {

    }
}
