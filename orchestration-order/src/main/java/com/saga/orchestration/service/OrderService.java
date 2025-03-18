package com.saga.orchestration.service;

import com.google.gson.Gson;
import com.saga.dto.request.CreateOrderRequest;
import com.saga.orchestration.adapter.producer.CreateOrderAdapterProducer;
import com.saga.orchestration.adapter.producer.CreateOutboxEventAdapterProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CreateOrderAdapterProducer createOrderAdapterProducer;
    private final CreateOutboxEventAdapterProducer createOutboxEventAdapterProducer;

    public String createOrder(CreateOrderRequest createOrderRequest) {
        String orderId = UUID.randomUUID().toString();
        String payload = new Gson().toJson(new CreateOrderRequest(orderId, createOrderRequest.userId(), createOrderRequest.orderRequests()));
        createOrderAdapterProducer.createOrder(payload);
        createOutboxEventAdapterProducer.createOutboxEvent(payload);
        return orderId;
    }
}
