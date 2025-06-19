package com.saga.admin.dto.request.errormessage;

import lombok.Data;

/**
 * Request DTO for updating ErrorMessage.
 */
@Data
public class UpdateErrorMessageRequest {
    private Long id;
    private String languageCode;
    private String text;
    private Long errorTypeId;
}
