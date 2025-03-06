package com.saga.inventory.dto.request;

public record UpdateInventoryRequest(int quantity, Long productId) {
}
