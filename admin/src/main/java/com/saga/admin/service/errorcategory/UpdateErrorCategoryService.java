package com.saga.admin.service.errorcategory;

import com.saga.admin.dto.request.errorcategory.UpdateErrorCategoryRequest;
import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.entity.ErrorCategory;
import com.saga.admin.entity.ErrorSystem;
import com.saga.admin.repository.ErrorCategoryRepository;
import com.saga.admin.repository.ErrorSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to update an existing ErrorCategory.
 */
@Service
@RequiredArgsConstructor
public class UpdateErrorCategoryService {
    private final ErrorCategoryRepository errorCategoryRepository;
    private final ErrorSystemRepository errorSystemRepository;
    private final ConvertErrorCategoryResponseService convertService;

    @Transactional
    public ErrorCategoryResponse update(Long id, UpdateErrorCategoryRequest request) {
        ErrorCategory entity = errorCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ErrorCategory"));
        ErrorSystem system = errorSystemRepository.findById(request.getSystemId())
                .orElseThrow(() -> new RuntimeException("ErrorSystem"));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setSystem(system);
        entity = errorCategoryRepository.save(entity);
        return convertService.convertToResponse(entity);
    }
}

