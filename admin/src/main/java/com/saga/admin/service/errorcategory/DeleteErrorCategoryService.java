package com.saga.admin.service.errorcategory;

import com.saga.admin.repository.ErrorCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service to delete an ErrorCategory by id.
 */
@Service
@RequiredArgsConstructor
public class DeleteErrorCategoryService {
    private final ErrorCategoryRepository errorCategoryRepository;

    public Long deleteById(Long id) {
        errorCategoryRepository.deleteById(id);
        return id;
    }
}

