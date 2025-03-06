package com.saga.orchestration.service;

import com.google.gson.Gson;
import com.saga.orchestration.adapter.producer.CreateOrderAdapterProducer;
import com.saga.orchestration.dto.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CreateOrderAdapterProducer createOrderAdapterProducer;

    public Long createOrder(CreateOrderRequest createOrderRequest) {
        String payload = new Gson().toJson(createOrderRequest);
        createOrderAdapterProducer.createOrder(payload);
        return 1L;
    }
}
