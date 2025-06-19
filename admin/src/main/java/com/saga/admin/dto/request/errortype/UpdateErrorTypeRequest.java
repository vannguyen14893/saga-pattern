package com.saga.admin.dto.request.errortype;

import lombok.Data;

/**
 * Request DTO for updating ErrorType.
 */
@Data
public class UpdateErrorTypeRequest {
    private Long id;
    private String name;
    private String baseCode;
    private Integer httpStatus;
    private Boolean deprecated;
    private Long categoryId;
}

