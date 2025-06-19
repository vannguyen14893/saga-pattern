package com.saga.admin.service.errorcategory;

import com.saga.admin.dto.response.errorcategory.ErrorCategoryResponse;
import com.saga.admin.entity.ErrorCategory;
import com.saga.admin.repository.ErrorCategoryRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for finding error categories by ID
 */
@Service
@RequiredArgsConstructor
public class FindErrorCategoryByIdService {
    private final ErrorCategoryRepository errorCategoryRepository;
    private final ConvertErrorCategoryResponseService convertErrorCategoryResponseService;

    /**
     * Finds an error category by its ID and converts it to a ErrorCategoryResponse
     *
     * @param id The ID of the error category to find
     * @return ErrorCategoryResponse containing the found error category data
     * @throws NotFoundExceptionHandler if error category with given ID is not found
     */
    public ErrorCategoryResponse findById(Long id) {
        ErrorCategory errorCategory = errorCategoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundExceptionHandler("ErrorCategory"));
        return convertErrorCategoryResponseService.convertToResponse(errorCategory);
    }
}
