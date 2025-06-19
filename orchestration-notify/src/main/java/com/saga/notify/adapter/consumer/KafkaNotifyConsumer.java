package com.saga.notify.adapter.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.dto.enums.EmailType;
import com.saga.dto.enums.NotifyStatus;
import com.saga.dto.enums.NotifyType;
import com.saga.dto.request.*;
import com.saga.notify.adapter.producer.KafkaEmailProducer;
import com.saga.notify.adapter.producer.KafkaPushProducer;
import com.saga.notify.adapter.producer.KafkaSmsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Kafka consumer component that processes notification messages from the orchestration-notify topic.
 * Handles three types of notifications: EMAIL, SMS, and PUSH.
 * Each notification type is processed and forwarded to its respective producer service.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaNotifyConsumer {

    public static final String NOTIFY_TYPE = "notifyType";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final KafkaEmailProducer kafkaEmailProducer;
    private final KafkaPushProducer kafkaPushProducer;
    private final KafkaSmsProducer kafkaSmsProducer;

    /**
     * Processes incoming notification messages from Kafka.
     * Deserializes JSON messages and routes them to appropriate producers based on notification type.
     *
     * @param messages List of JSON strings containing notification data
     * @throws IOException if there's an error parsing the JSON messages
     */
    @Async
    @KafkaListener(topics = "orchestration-notify", groupId = "${kafka.group-id}", batch = "true")
    public void notifyListener(List<String> messages) throws IOException {
        for (String messageStr : messages) {
            log.info("notify request: " + messageStr);
            JsonNode jsonNode = objectMapper.readTree(messageStr);
            if (NotifyType.EMAIL.name().equals(jsonNode.get(NOTIFY_TYPE).textValue())) {
                CreateEmailNotifyRequest request = objectMapper.readValue(messageStr, CreateEmailNotifyRequest.class);
                if (StringUtils.hasText(request.email())) {
                    EmailNotify emailNotify = EmailNotify.builder()
                            .id(UUID.randomUUID().toString())
                            .to(new String[]{request.email()})
                            .subject(request.subject())
                            .content(request.content())
                            .emailType(EmailType.TO_ONE)
                            .isTemplate(true)
                            .build();
                    if (emailNotify.getTo() != null) {
                        String json = objectMapper.writeValueAsString(emailNotify);
                        log.info("notify email request: " + json);
                        kafkaEmailProducer.sendEmail(json);
                    }
                }
            }

            if (NotifyType.SMS.name().equals(jsonNode.get(NOTIFY_TYPE).textValue())) {
                CreateSmsNotifyRequest request = objectMapper.readValue(messageStr, CreateSmsNotifyRequest.class);
                SmsNotify smsNotify = new SmsNotify(request.phone(), request.message(), request.smsType(), NotifyStatus.PENDING);
                String json = objectMapper.writeValueAsString(smsNotify);
                log.info("notify sms request: " + json);
                kafkaSmsProducer.sendSms(json);
            }

            if (NotifyType.PUSH.name().equals(jsonNode.get(NOTIFY_TYPE).textValue())) {
                log.info("notify push request: " + messageStr);
                kafkaPushProducer.pushNotify(messageStr);
            }
        }

    }
}
