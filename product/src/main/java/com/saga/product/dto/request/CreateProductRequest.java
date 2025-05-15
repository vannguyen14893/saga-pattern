package com.saga.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Request object for creating a new product")
public record CreateProductRequest(
        @Schema(description = "Name of the product", example = "Smartphone")
        @NotBlank String name,

        @Schema(description = "Detailed description of the product", example = "Latest model smartphone with advanced features")
        String description,

        @Schema(description = "Price of the product", example = "999.99")
        @NotNull BigDecimal price,

        @Schema(description = "ID of the category the product belongs to", example = "1")
        @NotNull Long categoryId,

        @Schema(description = "Available quantity of the product", example = "100")
        int quantity
) {
}
