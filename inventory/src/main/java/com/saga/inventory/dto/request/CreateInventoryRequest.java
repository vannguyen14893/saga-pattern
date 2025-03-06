package com.saga.inventory.dto.request;

public record CreateInventoryRequest(int quantity, Long productId, String actionType) {
}
