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

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaNotifyConsumer {

    public static final String NOTIFY_TYPE = "notifyType";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final KafkaEmailProducer kafkaEmailProducer;
    private final KafkaPushProducer kafkaPushProducer;
    private final KafkaSmsProducer kafkaSmsProducer;

    @Async
    @KafkaListener(topics = "orchestration-notify", groupId = "${kafka.group-id}", batch = "true")
    public void notifyListener(List<String> messages) throws IOException {
//CreateOutboxEventRequest outBoxEventRequest = new CreateOutboxEventRequest(UUID.randomUUID().toString(), AggregateType.ORDER.name(), createOrderRequest.orderId(),
//                OrderStatus.ORDER_UPDATE_INVENTORY.name(), payload);
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
