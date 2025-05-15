package com.saga.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error response structure for API endpoints")
public record ResponseError<T>(
        @Schema(description = "Unique identifier for the error", example = "550e8400-e29b-41d4-a716-446655440000")
        String id,

        @Schema(description = "HTTP status code", examples = {"400", "401", "403", "404", " 500"})
        int status,

        @Schema(description = "Error message or details. Can be a string or a structured object depending on the error type")
        T message
) {
}

