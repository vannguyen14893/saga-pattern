package com.saga.orchestration.adapter.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Producer adapter that sends order creation events to Kafka.
 * Part of the saga pattern implementation for coordinating order processing steps.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Sends an order creation event to the 'order' Kafka topic.
     *
     * @param payload The order event data in String format
     */
    public void createOrder(String payload) {
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send("order", payload);
        log.info("result -------------- {}",result);
    }
}
