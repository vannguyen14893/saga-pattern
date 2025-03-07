package com.saga.orchestration.service;

import com.google.gson.Gson;
import com.saga.orchestration.adapter.producer.CreateOrderAdapterProducer;
import com.saga.orchestration.dto.request.CreateOrderRequest;
import com.saga.orchestration.entity.OutboxEvent;
import com.saga.orchestration.enums.AggregateType;
import com.saga.orchestration.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CreateOrderAdapterProducer createOrderAdapterProducer;
    private final OutBoxEventService outBoxEventService;


    public String createOrder(CreateOrderRequest createOrderRequest) {
        String orderId = UUID.randomUUID().toString();
        String payload = new Gson().toJson(new CreateOrderRequest(orderId,createOrderRequest.userId(),createOrderRequest.orderRequests()));
        createOrderAdapterProducer.createOrder(payload);
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateId(orderId);
        outboxEvent.setAggregateType(AggregateType.ORDER.name());
        outboxEvent.setEventType(OrderStatus.ORDER_CREATE_NEW.name());
        outboxEvent.setPayload(payload);
        outboxEvent.setCreatedDate(LocalDateTime.now());
        outBoxEventService.create(outboxEvent);
        return orderId;
    }
}
