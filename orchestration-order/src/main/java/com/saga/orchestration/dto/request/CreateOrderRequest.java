package com.saga.orchestration.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(Long userId, List<CreateOrderDetailRequest> orderRequests) {
    public record CreateOrderDetailRequest(Long productId, int quantity, BigDecimal price) {
    }
}
