package com.saga.inventory.adapter.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka producer adapter for inventory-related messages.
 * Handles the production of messages to Kafka topics for inventory confirmation.
 */
@Service
@RequiredArgsConstructor
public class InventoryAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Sends an inventory confirmation message to the Kafka topic.
     *
     * @param payload the message payload containing inventory confirmation details
     */
    public void confirmInventory(String payload) {
        kafkaTemplate.send("confirm-inventory", payload);
    }}
