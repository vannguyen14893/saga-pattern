package com.saga.admin.dto.request.errorcategory;

import lombok.Data;

/**
 * Request DTO for creating ErrorCategory.
 */
@Data
public class CreateErrorCategoryRequest {
    private String name;
    private String description;
    private Long systemId;
}

