package com.saga.orchestration.adapter.consumer;

import com.google.gson.Gson;
import com.saga.orchestration.dto.request.CreateOrderRequest;
import com.saga.orchestration.entity.OutboxEvent;
import com.saga.orchestration.enums.AggregateType;
import com.saga.orchestration.enums.OrderStatus;
import com.saga.orchestration.service.OutBoxEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmUpdateInventoryAdapterConsumer {
    private final OutBoxEventService outBoxEventService;

    @KafkaListener(topics = "confirm-inventory", groupId = "orchestration")
    public void confirmUpdateInventory(String payload) {
        CreateOrderRequest createOrderRequest = new Gson().fromJson(payload, CreateOrderRequest.class);
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateId(createOrderRequest.orderId());
        outboxEvent.setAggregateType(AggregateType.ORDER.name());
        outboxEvent.setEventType(OrderStatus.ORDER_UPDATE_INVENTORY.name());
        outboxEvent.setPayload(payload);
        outboxEvent.setCreatedDate(LocalDateTime.now());
        outBoxEventService.create(outboxEvent);
    }
}
