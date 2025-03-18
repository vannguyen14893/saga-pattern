package com.saga.inventory.adapter.consumer;

import com.google.gson.Gson;
import com.saga.dto.request.InventoryAdapterRequest;
import com.saga.inventory.adapter.producer.InventoryAdapterProducer;
import com.saga.inventory.dto.request.CreateInventoryRequest;
import com.saga.inventory.dto.request.UpdateInventoryRequest;
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

    @KafkaListener(topics = "product", groupId = "${kafka.group-id}")
    public void create(String payload) {
        InventoryAdapterRequest inventoryAdapterRequest = new Gson().fromJson(payload, InventoryAdapterRequest.class);
        switch (inventoryAdapterRequest.actionType()) {
            case CREATE_NEW ->
                    inventoryService.create(new CreateInventoryRequest(inventoryAdapterRequest.quantity(), inventoryAdapterRequest.productId(), inventoryAdapterRequest.actionType().name()));
            case DELETE -> inventoryService.deleteByProductId(inventoryAdapterRequest.productId());
            default -> log.info("log---");
        }
    }

    @KafkaListener(topics = "inventory", groupId = "${kafka.group-id}")
    public void update(String payload) {
        UpdateInventoryRequest updateInventoryRequests = new Gson().fromJson(payload, UpdateInventoryRequest.class);
        for (UpdateInventoryRequest.OrderDetailRequest updateInventoryRequest : updateInventoryRequests.orderRequests()) {
            inventoryService.update(updateInventoryRequest);
        }
        inventoryAdapterProducer.confirmUInventory(payload);

    }
}
