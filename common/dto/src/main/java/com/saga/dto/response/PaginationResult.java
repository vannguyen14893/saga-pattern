package com.saga.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Wrapper class for paginated results")
public record PaginationResult<T>(
        @Schema(description = "The paginated data content")
        T data,

        @Schema(description = "Total number of elements across all pages",
                example = "42")
        long total
) { }

