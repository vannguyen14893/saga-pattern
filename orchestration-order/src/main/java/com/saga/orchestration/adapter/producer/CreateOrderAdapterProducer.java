package com.saga.orchestration.adapter.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void createOrder(String payload) {
        kafkaTemplate.send("order", payload);
    }
}
