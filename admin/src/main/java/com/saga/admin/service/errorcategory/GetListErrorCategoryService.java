package com.saga.admin.service.errorcategory;

import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.repository.ErrorCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to get list of ErrorCategory as response DTOs.
 */
@Service
@RequiredArgsConstructor
public class GetListErrorCategoryService {
    private final ErrorCategoryRepository errorCategoryRepository;
    private final ConvertErrorCategoryResponseService convertService;

    public List<ErrorCategoryResponse> findAll() {
        return errorCategoryRepository.findAll().stream()
                .map(convertService::convertToResponse)
                .toList();
    }
}

