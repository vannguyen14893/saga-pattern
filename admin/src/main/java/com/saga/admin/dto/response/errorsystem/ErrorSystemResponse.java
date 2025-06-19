package com.saga.admin.dto.response.errorsystem;

import lombok.Data;

import java.util.List;

/**
 * DTO for ErrorSystem API response.
 * Contains error system details and related category IDs.
 */
@Data
public class ErrorSystemResponse {
    private Long id;
    private String name;
    private String description;
    private String systemName;
}
