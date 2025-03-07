package com.saga.inventory.adapter.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void confirmUInventory(String payload) {
        kafkaTemplate.send("confirm-inventory", payload);
    }}
