package com.saga.notify.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka producer service responsible for sending notification history events.
 * Uses Spring Kafka template to publish messages to a Kafka topic.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaNotifyHistoryProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;


    /**
     * Sends a notification history event to the 'outbox-event' Kafka topic.
     *
     * @param notifyHistory the notification history message to be sent
     */
    public void sendNotifyHistory(String notifyHistory) {
        kafkaTemplate.send("outbox-event", notifyHistory);
    }
}
