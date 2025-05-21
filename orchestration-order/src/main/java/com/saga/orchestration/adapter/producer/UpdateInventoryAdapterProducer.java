package com.saga.orchestration.adapter.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Producer adapter that sends inventory update events to Kafka.
 * Part of the saga pattern implementation for coordinating inventory management steps.
 */
@Service
@RequiredArgsConstructor
public class UpdateInventoryAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Sends an inventory update event to the 'inventory' Kafka topic.
     *
     * @param payload The inventory event data in String format
     */
    public void updateInventory(String payload) {
        kafkaTemplate.send("inventory", payload);
    }
}
