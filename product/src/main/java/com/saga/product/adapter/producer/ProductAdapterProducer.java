package com.saga.product.adapter.producer;

import com.google.gson.Gson;
import com.saga.dto.enums.ActionType;
import com.saga.dto.request.InventoryAdapterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendToInventory(Long productId, int quantity, ActionType actionType) {
        InventoryAdapterRequest inventoryAdapterRequest = new InventoryAdapterRequest(productId, quantity,actionType);
        String json = new Gson().toJson(inventoryAdapterRequest);
        kafkaTemplate.send("product", json);
    }
}
