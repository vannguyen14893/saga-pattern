package com.saga.admin.dto.response.errorcategory;

import lombok.Data;

/**
 * DTO for ErrorCategory API response.
 */
@Data
public class ErrorCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private Long systemId;
}

