package com.saga.admin.dto.request.errormessage;

import lombok.Data;

/**
 * Request DTO for creating ErrorMessage.
 */
@Data
public class CreateErrorMessageRequest {
    private String languageCode;

    private String text;
    private Long errorTypeId;
}

