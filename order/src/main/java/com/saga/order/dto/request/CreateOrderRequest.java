package com.saga.order.dto.request;


import com.saga.exceptions.annotaions.ValidEmail;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(@ValidEmail(message = "email.valid") String orderId,@NotNull(message = "phone.valid") Long userId,
                                 List<CreateOrderDetailRequest> orderRequests) {
    public record CreateOrderDetailRequest(Long productId, int quantity, BigDecimal price) {
    }
}
