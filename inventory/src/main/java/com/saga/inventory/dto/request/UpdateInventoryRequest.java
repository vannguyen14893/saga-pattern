package com.saga.inventory.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record UpdateInventoryRequest(String orderId, Long userId, List<OrderDetailRequest> orderRequests) {
    public record OrderDetailRequest(Long productId, int quantity, BigDecimal price) {
    }
}