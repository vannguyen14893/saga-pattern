package com.saga.inventory.adapter.consumer;

import com.google.gson.Gson;
import com.saga.inventory.adapter.producer.InventoryAdapterProducer;
import com.saga.inventory.dto.request.CreateInventoryRequest;
import com.saga.inventory.dto.request.UpdateInventoryRequest;
import com.saga.inventory.enums.ActionType;
import com.saga.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryAdapterConsumer {
    private final InventoryService inventoryService;
    private final InventoryAdapterProducer inventoryAdapterProducer;

    @KafkaListener(topics = "product", groupId = "inventory")
    public void create(String payload) {
        CreateInventoryRequest createInventoryRequest = new Gson().fromJson(payload, CreateInventoryRequest.class);
        switch (ActionType.valueOf(createInventoryRequest.actionType())) {
            case CREATE_NEW -> inventoryService.create(createInventoryRequest);
            case DELETE -> inventoryService.deleteByProductId(createInventoryRequest.productId());
            default -> log.info("log---");
        }
    }

    @KafkaListener(topics = "inventory", groupId = "inventory")
    public void update(String payload) {
        UpdateInventoryRequest updateInventoryRequests = new Gson().fromJson(payload, UpdateInventoryRequest.class);
        for (UpdateInventoryRequest.OrderDetailRequest updateInventoryRequest : updateInventoryRequests.orderRequests()) {
            inventoryService.update(updateInventoryRequest);
        }
        inventoryAdapterProducer.confirmUInventory(payload);

    }
}
