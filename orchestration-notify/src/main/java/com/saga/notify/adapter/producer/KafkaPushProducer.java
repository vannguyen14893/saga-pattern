package com.saga.notify.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Kafka producer component responsible for sending push notifications asynchronously.
 * Uses Spring's KafkaTemplate to publish messages to a Kafka topic named "push".
 * The producer operates asynchronously using Spring's @Async annotation to avoid
 * blocking the calling thread.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaPushProducer {


    private final KafkaTemplate<String, String> pushKafkaTemplate;

    @Async
    public void pushNotify(String pushNotify) {
        pushKafkaTemplate.send("push", pushNotify);
    }
}
