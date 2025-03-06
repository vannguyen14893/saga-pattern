package com.saga.order.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record UpdateOrderRequest(Long orderId, List<UpdateOrderDetailRequest> updateOrderDetailRequests) {
    public record UpdateOrderDetailRequest(Long productId, int quantity, BigDecimal price) {

    }
}
