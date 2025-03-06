package com.saga.product.adapter.dto;

import com.saga.product.enums.ActionType;

public record InventoryAdapterRequest(Long productId, int quantity, ActionType actionType) {
}
