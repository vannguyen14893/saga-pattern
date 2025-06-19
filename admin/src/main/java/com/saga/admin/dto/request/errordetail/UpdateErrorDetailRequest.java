package com.saga.admin.dto.request.errordetail;

import lombok.Data;

/**
 * Request DTO for updating ErrorDetail.
 */
@Data
public class UpdateErrorDetailRequest {
    private Long id;
    private String key;
    private String description;
    private Boolean required ;
    private String exampleValue;
    private Long errorTypeId;
}

