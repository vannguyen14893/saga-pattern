package com.saga.admin.dto.request.errordetail;

import lombok.Data;

/**
 * Request DTO for creating ErrorDetail.
 */
@Data
public class CreateErrorDetailRequest {
    private String key;
    private String description;
    private Boolean required ;
    private String exampleValue;
    private Long errorTypeId;
}

