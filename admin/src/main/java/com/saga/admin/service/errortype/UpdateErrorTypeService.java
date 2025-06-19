package com.saga.admin.service.errortype;

import com.saga.admin.dto.request.errortype.UpdateErrorTypeRequest;
import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.entity.ErrorCategory;
import com.saga.admin.entity.ErrorType;
import com.saga.admin.repository.ErrorCategoryRepository;
import com.saga.admin.repository.ErrorTypeRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for updating existing error types
 */
@Service
@RequiredArgsConstructor
public class UpdateErrorTypeService {
    private final ErrorTypeRepository errorTypeRepository;
    private final ConvertErrorTypeResponseService convertErrorTypeResponseService;
    private final ErrorCategoryRepository errorCategoryRepository;

    /**
     * Updates an existing error type with new data from the provided request
     *
     * @param request The request containing updated error type details
     * @return ErrorTypeResponse containing the updated error type data
     * @throws NotFoundExceptionHandler if error type with given ID is not found
     */
    public ErrorTypeResponse update(UpdateErrorTypeRequest request) {
        ErrorType errorType = errorTypeRepository.findById(request.getId())
            .orElseThrow(() -> new NotFoundExceptionHandler("ErrorType"));
        errorType.setId(request.getId());
        errorType.setName(request.getName());
        errorType.setBaseCode(request.getBaseCode());
        errorType.setHttpStatus(request.getHttpStatus());
        errorType.setDeprecated(request.getDeprecated());
        ErrorCategory errorCategory = errorCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundExceptionHandler("ErrorCategory"));
        errorType.setCategory(errorCategory);
        return convertErrorTypeResponseService.convertToErrorTypeResponse(errorTypeRepository.save(errorType));
    }
}

