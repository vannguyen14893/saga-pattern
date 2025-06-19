package com.saga.admin.dto.response.errordetail;
import lombok.Data;

/**
 * DTO for ErrorDetail API response.
 */
@Data
public class ErrorDetailResponse {
    private Long id;
    private String key;
    private String description;
    private Boolean required ;
    private String exampleValue;
}

