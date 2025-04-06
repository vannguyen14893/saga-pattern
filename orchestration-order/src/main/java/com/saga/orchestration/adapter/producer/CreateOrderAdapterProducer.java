package com.saga.orchestration.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void createOrder(String payload) {
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send("order", payload);
        log.info("result -------------- {}",result);
    }
}
