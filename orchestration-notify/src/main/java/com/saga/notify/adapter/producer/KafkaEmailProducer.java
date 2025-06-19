package com.saga.notify.adapter.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Kafka producer service for sending email notifications.
 * Provides asynchronous functionality to send email messages to a Kafka topic.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaEmailProducer {


    private final KafkaTemplate<String, String> emailKafkaTemplate;

    /**
     * Asynchronously sends an email notification message to the Kafka topic.
     *
     * @param emailNotify The email notification message in JSON string format
     */
    @Async
    public void sendEmail(String emailNotify) {
        emailKafkaTemplate.send("email", emailNotify);
    }
}
