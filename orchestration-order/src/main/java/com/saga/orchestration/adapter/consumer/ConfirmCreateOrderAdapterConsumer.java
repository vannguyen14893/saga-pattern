package com.saga.orchestration.adapter.consumer;

import com.google.gson.Gson;
import com.saga.dto.request.CreateOrderRequest;
import com.saga.orchestration.adapter.producer.UpdateInventoryAdapterProducer;
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
public class ConfirmCreateOrderAdapterConsumer {
    private final UpdateInventoryAdapterProducer updateInventoryAdapterProducer;
    private final OutBoxEventService outBoxEventService;

    @KafkaListener(topics = "confirm-order", groupId = "orchestration")
    public void confirmCreateOrder(String payload) {
        CreateOrderRequest createOrderRequest = new Gson().fromJson(payload, CreateOrderRequest.class);
        updateInventoryAdapterProducer.updateInventory(payload);
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateId(createOrderRequest.orderId());
        outboxEvent.setAggregateType(AggregateType.ORDER.name());
        outboxEvent.setEventType(OrderStatus.ORDER_CREATED.name());
        outboxEvent.setPayload(payload);
        outboxEvent.setCreatedDate(LocalDateTime.now());
        outBoxEventService.create(outboxEvent);
    }
}
