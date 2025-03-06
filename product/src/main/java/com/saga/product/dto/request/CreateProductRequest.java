package com.saga.product.dto.request;

import java.math.BigDecimal;


public record CreateProductRequest(String name, String description, BigDecimal price, Long categoryId, int quantity) {
}
