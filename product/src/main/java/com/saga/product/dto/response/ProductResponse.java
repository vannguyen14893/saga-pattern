package com.saga.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Response object containing product information")
public record ProductResponse(
        @Schema(description = "Unique identifier of the product", example = "1")
        Long id,

        @Schema(description = "Name of the product", example = "Smartphone X1")
        String name,

        @Schema(description = "Detailed description of the product", example = "Latest generation smartphone with advanced features")
        String description,

        @Schema(description = "Product price in the default currency", example = "999.99")
        BigDecimal price,

        @Schema(description = "Timestamp when the product was created", example = "2024-03-21T10:30:00")
        LocalDateTime createdDate
) {
}

