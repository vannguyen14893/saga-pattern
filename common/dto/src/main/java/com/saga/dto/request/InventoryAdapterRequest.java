package com.saga.dto.request;


import com.saga.dto.enums.ActionType;

public record InventoryAdapterRequest(Long productId, int quantity, ActionType actionType) {
}
