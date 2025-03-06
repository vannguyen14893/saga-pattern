package com.saga.orchestration.adapter.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateInventoryAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void updateInventory(String payload) {
        kafkaTemplate.send("inventory", payload);
    }
}
