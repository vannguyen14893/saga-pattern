package com.saga.product.dto.request;

import java.math.BigDecimal;


public record UpdateProductRequest(Long id, String name, String description, BigDecimal price, Long categoryId,
                                   int count) {
}
