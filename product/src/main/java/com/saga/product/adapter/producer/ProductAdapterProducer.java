package com.saga.product.adapter.producer;

import com.google.gson.Gson;
import com.saga.dto.enums.ActionType;
import com.saga.dto.request.InventoryAdapterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Producer adapter for sending product-related messages to Kafka topics.
 * This service handles the communication with the inventory system through Kafka messaging.
 */
@Service
@RequiredArgsConstructor
public class ProductAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Sends product inventory update request to Kafka topic.
     *
     * @param productId  the ID of the product to update
     * @param quantity   the quantity to modify in inventory
     * @param actionType the type of action to perform (e.g., ADD, REMOVE)
     */
    public void sendToInventory(Long productId, int quantity, ActionType actionType) {
        InventoryAdapterRequest inventoryAdapterRequest = new InventoryAdapterRequest(productId, quantity,actionType);
        String json = new Gson().toJson(inventoryAdapterRequest);
        kafkaTemplate.send("product", json);
    }
}
