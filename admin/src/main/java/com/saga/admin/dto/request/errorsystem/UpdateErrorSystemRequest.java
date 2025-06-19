package com.saga.admin.dto.request.errorsystem;

import lombok.Data;

/**
 * Request DTO for updating ErrorSystem.
 */
@Data
public class UpdateErrorSystemRequest {
    private String name;
    private String description;
    private String systemName;
}

