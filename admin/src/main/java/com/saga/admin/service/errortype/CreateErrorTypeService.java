package com.saga.admin.service.errortype;

import com.saga.admin.dto.request.errortype.CreateErrorTypeRequest;
import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.entity.ErrorCategory;
import com.saga.admin.entity.ErrorType;
import com.saga.admin.repository.ErrorCategoryRepository;
import com.saga.admin.repository.ErrorTypeRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for creating new error types
 */
@Service
@RequiredArgsConstructor
public class CreateErrorTypeService {
    private final ErrorTypeRepository errorTypeRepository;
    private final ConvertErrorTypeResponseService convertErrorTypeResponseService;
    private final ErrorCategoryRepository errorCategoryRepository;
    /**
     * Creates a new error type from the provided request
     *
     * @param request The request containing error type details
     * @return ErrorTypeResponse containing the created error type data
     */
    public ErrorTypeResponse create(CreateErrorTypeRequest request) {
        ErrorType errorType = new ErrorType();
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

