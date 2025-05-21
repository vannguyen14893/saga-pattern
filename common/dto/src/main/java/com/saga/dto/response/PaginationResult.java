package com.saga.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Wrapper class for paginated results")
public record PaginationResult<T>(
        @Schema(description = "Unique identifier for the response", example = "123e4567-e89b-12d3-a456-426614174000")
        String id,

        @Schema(description = "HTTP status code", example = "200")
        String status,

        @Schema(description = "Response message", example = "Operation completed successfully")
        String message,
        @Schema(description = "The paginated data content")
        T data,

        @Schema(description = "Total number of elements across all pages",
                example = "42")
        long total
) { }

