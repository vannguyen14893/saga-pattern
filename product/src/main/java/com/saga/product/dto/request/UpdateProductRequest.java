package com.saga.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Request object for updating a product")
public record UpdateProductRequest(
        @Schema(description = "Product ID", example = "1")
        Long id,

        @Schema(description = "Product name", example = "Smartphone")
        String name,

        @Schema(description = "Product description", example = "Latest model smartphone with advanced features")
        String description,

        @Schema(description = "Product price", example = "999.99")
        BigDecimal price,

        @Schema(description = "Category ID to which the product belongs", example = "1")
        Long categoryId,

        @Schema(description = "Available quantity of the product", example = "50", minimum = "0")
        int count
) {
}

