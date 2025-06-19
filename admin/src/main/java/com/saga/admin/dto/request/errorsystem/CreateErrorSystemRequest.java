package com.saga.admin.dto.request.errorsystem;

import lombok.Data;

/**
 * Request DTO for creating ErrorSystem.
 */
@Data
public class CreateErrorSystemRequest {
    private String name;
    private String description;
    private String systemName;
}

