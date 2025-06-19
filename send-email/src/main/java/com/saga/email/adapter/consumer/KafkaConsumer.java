package com.saga.email.adapter.consumer;

import com.google.gson.Gson;
import com.saga.dto.request.EmailNotify;
import com.saga.email.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Kafka consumer service that handles email notifications.
 * Listens to the "email" topic and processes incoming email notification requests
 * by converting them to EmailNotify objects and forwarding them to the SendEmailService.
 */


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final SendEmailService sendEmailService;


    /**
     * Processes incoming email notification messages from Kafka.
     * Deserializes JSON messages into EmailNotify objects and forwards them to the email service.
     *
     * @param emailNotifies List of JSON strings containing email notification data
     */
    @KafkaListener(
            topics = "email",
            groupId = "${kafka.group-id}", batch = "true")
    public void listenNotify(List<String> emailNotifies) {
        for (String emailNotify : emailNotifies) {
            EmailNotify request = new Gson().fromJson(emailNotify, EmailNotify.class);
            sendEmailService.execute(request);
        }
    }
}
