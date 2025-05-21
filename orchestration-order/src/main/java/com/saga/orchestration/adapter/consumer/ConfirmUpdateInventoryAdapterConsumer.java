package com.saga.orchestration.adapter.consumer;

import com.saga.orchestration.adapter.producer.CreateOutboxEventAdapterProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Consumer adapter that handles inventory update confirmation events from Kafka.
 * Part of the saga pattern implementation for coordinating inventory management steps.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmUpdateInventoryAdapterConsumer {
    private final CreateOutboxEventAdapterProducer createOutboxEventAdapterProducer;

    /**
     * Listens for inventory update confirmation events and triggers outbox event creation.
     *
     * @param payload The inventory confirmation event data in String format
     */
    @KafkaListener(topics = "confirm-inventory", groupId = "${kafka.group-id}")
    public void confirmUpdateInventory(String payload) {
        createOutboxEventAdapterProducer.createOutboxEvent(payload);
    }
}
