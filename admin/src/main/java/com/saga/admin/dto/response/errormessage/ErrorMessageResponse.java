package com.saga.admin.dto.response.errormessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for ErrorMessage API response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageResponse {
    private Long id;
    private String languageCode;
    private String text;
}

