package com.saga.notify.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Kafka producer service that handles SMS notifications.
 * Provides asynchronous functionality to send SMS messages through Kafka messaging system.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSmsProducer {

    private final KafkaTemplate<String, String> smsKafkaTemplate;

    /**
     * Asynchronously sends an SMS notification message to Kafka.
     *
     * @param smsNotify the SMS message content to be sent
     */
    @Async
    public void sendSms(String smsNotify) {
        smsKafkaTemplate.send("sms", smsNotify);
    }
}
