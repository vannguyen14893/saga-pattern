package com.saga.order.adapter.consumer;

import com.google.gson.Gson;
import com.saga.order.dto.request.CreateOrderRequest;
import com.saga.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderAdapterConsumer {
    private final OrderService orderService;

    @KafkaListener(topics = "order", groupId = "${kafka.group-id}")
    public void create(String payload) {
        CreateOrderRequest createOrderRequest = new Gson().fromJson(payload, CreateOrderRequest.class);
        orderService.create(createOrderRequest);
    }

}
