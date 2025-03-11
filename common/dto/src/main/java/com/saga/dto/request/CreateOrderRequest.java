package com.saga.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(String orderId, Long userId, List<CreateOrderDetailRequest> orderRequests) {
    public record CreateOrderDetailRequest(Long productId, int quantity, BigDecimal price) {
    }
}
