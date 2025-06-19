package com.saga.admin.service.errordetail;

import com.saga.admin.dto.request.errordetail.CreateErrorDetailRequest;
import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.entity.ErrorDetail;
import com.saga.admin.entity.ErrorType;
import com.saga.admin.repository.ErrorDetailRepository;
import com.saga.admin.repository.ErrorTypeRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for creating new error details
 */
@Service
@RequiredArgsConstructor
public class CreateErrorDetailService {
    private final ErrorDetailRepository errorDetailRepository;
    public final ConvertErrorDetailResponseService convertErrorDetailResponseService;
    private final ErrorTypeRepository errorTypeRepository;

    /**
     * Creates a new error detail from the provided request
     *
     * @param request The request containing error detail data
     * @return ErrorDetailResponse containing the created error detail data
     */
    public ErrorDetailResponse create(CreateErrorDetailRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        ErrorType errorType = errorTypeRepository.findById(request.getErrorTypeId())
                .orElseThrow(() -> new NotFoundExceptionHandler("ErrorType"));
        errorDetail.setErrorType(errorType);
        errorDetail.setKey(request.getKey());
        errorDetail.setDescription(request.getDescription());
        errorDetail.setRequired(request.getRequired());
        errorDetail.setExampleValue(request.getExampleValue());
        return convertErrorDetailResponseService.convertToErrorDetailResponse(errorDetailRepository.save(errorDetail));
    }
}

