package com.saga.orchestration.service;

import com.google.gson.Gson;
import com.saga.dto.request.CreateOrderRequest;
import com.saga.orchestration.adapter.producer.CreateOrderAdapterProducer;
import com.saga.orchestration.adapter.producer.CreateOutboxEventAdapterProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class responsible for handling order creation operations and managing
 * the order creation process through message producers.
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    /**
     * Producer responsible for sending order creation messages
     */
    private final CreateOrderAdapterProducer createOrderAdapterProducer;
    /**
     * Producer responsible for creating outbox events for order creation
     */
    private final CreateOutboxEventAdapterProducer createOutboxEventAdapterProducer;

    /**
     * Creates a new order and publishes related events.
     *
     * @param createOrderRequest the request containing order details
     * @return the generated order ID
     */
    public String createOrder(CreateOrderRequest createOrderRequest) {
        String orderId = UUID.randomUUID().toString();
        String payload = new Gson().toJson(new CreateOrderRequest(orderId, createOrderRequest.userId(), createOrderRequest.orderRequests()));
        createOrderAdapterProducer.createOrder(payload);
        createOutboxEventAdapterProducer.createOutboxEvent(payload);
        return orderId;
    }
}
