package com.saga.admin.dto.response.errortype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for ErrorType API response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorTypeResponse {
    private Long id;
    private String name;
    private String baseCode;
    private Integer httpStatus;
    private Boolean deprecated;
    private Long categoryId;
}

