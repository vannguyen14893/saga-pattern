package com.saga.orchestration.adapter.producer;

import com.google.gson.Gson;
import com.saga.dto.request.CreateOrderRequest;
import com.saga.dto.request.CreateOutboxEventRequest;
import com.saga.orchestration.enums.AggregateType;
import com.saga.orchestration.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Producer adapter that handles the creation of outbox events for the saga pattern.
 * Converts order requests to outbox events and publishes them to Kafka.
 * Part of the distributed transaction management using the outbox pattern.
 */
@Service
@RequiredArgsConstructor
public class CreateOutboxEventAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Creates and sends an outbox event to Kafka based on the order request payload.
     * Converts the payload to CreateOrderRequest, creates an outbox event, and publishes it.
     *
     * @param payload The order request data in JSON string format
     */
    public void createOutboxEvent(String payload) {
        CreateOrderRequest createOrderRequest = new Gson().fromJson(payload, CreateOrderRequest.class);
        CreateOutboxEventRequest outBoxEventRequest = new CreateOutboxEventRequest(UUID.randomUUID().toString(), AggregateType.ORDER.name(), createOrderRequest.orderId(),
                OrderStatus.ORDER_UPDATE_INVENTORY.name(), payload);
        String data = new Gson().toJson(outBoxEventRequest);
        kafkaTemplate.send("outbox-event", data);
    }
}
