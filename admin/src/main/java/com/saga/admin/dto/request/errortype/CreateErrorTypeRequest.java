package com.saga.admin.dto.request.errortype;

import lombok.Data;

/**
 * Request DTO for creating ErrorType.
 */
@Data
public class CreateErrorTypeRequest {
    private String name;
    private String baseCode;
    private Integer httpStatus;
    private Boolean deprecated;
    private Long categoryId;
}

