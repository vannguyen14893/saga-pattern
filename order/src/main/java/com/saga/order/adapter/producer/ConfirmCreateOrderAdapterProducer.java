package com.saga.order.adapter.producer;

import com.google.gson.Gson;
import com.saga.order.dto.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka producer adapter for handling order confirmation messages.
 * Sends order confirmation messages to the confirm-order topic.
 */
@Service
@RequiredArgsConstructor
public class ConfirmCreateOrderAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Sends an order confirmation message to Kafka.
     * Converts the order request to JSON and publishes it to the confirm-order topic.
     *
     * @param createOrderRequest the order request to be confirmed
     */
    public void confirmCreateOrder(CreateOrderRequest createOrderRequest) {
        String payload = new Gson().toJson(createOrderRequest);
        kafkaTemplate.send("confirm-order", payload);
    }
}
