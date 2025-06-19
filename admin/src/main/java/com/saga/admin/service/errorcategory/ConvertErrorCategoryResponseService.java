package com.saga.admin.service.errorcategory;

import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.entity.ErrorCategory;
import org.springframework.stereotype.Service;

/**
 * Service to convert ErrorCategory entity to ErrorCategoryResponse DTO.
 */
@Service
public class ConvertErrorCategoryResponseService {
    public ErrorCategoryResponse convertToResponse(ErrorCategory entity) {
        ErrorCategoryResponse dto = new ErrorCategoryResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setSystemId(entity.getSystem() != null ? entity.getSystem().getId() : null);
        return dto;
    }
}

