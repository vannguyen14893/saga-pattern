package com.saga.admin.dto.request.errorcategory;

import lombok.Data;

/**
 * Request DTO for updating ErrorCategory.
 */
@Data
public class UpdateErrorCategoryRequest {
    private String name;
    private String description;
    private Long systemId;
}

