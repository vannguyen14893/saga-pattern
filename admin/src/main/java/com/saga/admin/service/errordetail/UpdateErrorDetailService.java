package com.saga.admin.service.errordetail;

import com.saga.admin.dto.request.errordetail.UpdateErrorDetailRequest;
import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.entity.ErrorDetail;
import com.saga.admin.entity.ErrorType;
import com.saga.admin.repository.ErrorDetailRepository;
import com.saga.admin.repository.ErrorTypeRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for updating existing error details
 */
@Service
@RequiredArgsConstructor
public class UpdateErrorDetailService {
    private final ErrorDetailRepository errorDetailRepository;
    public final ConvertErrorDetailResponseService convertErrorDetailResponseService;
    private final ErrorTypeRepository errorTypeRepository;

    /**
     * Updates an existing error detail with new data from the provided request
     *
     * @param request The request containing updated error detail data
     * @return ErrorDetailResponse containing the updated error detail data
     * @throws NotFoundExceptionHandler if error detail with given ID is not found
     */
    public ErrorDetailResponse update(UpdateErrorDetailRequest request) {
        ErrorDetail errorDetail = errorDetailRepository.findById(request.getId()).orElseThrow(() -> new NotFoundExceptionHandler("ErrorDetail"));
        ErrorType errorType = errorTypeRepository.findById(request.getErrorTypeId())
                .orElseThrow(() -> new NotFoundExceptionHandler("ErrorType"));
        errorDetail.setKey(request.getKey());
        errorDetail.setDescription(request.getDescription());
        errorDetail.setRequired(request.getRequired());
        errorDetail.setExampleValue(request.getExampleValue());
        errorDetail.setErrorType(errorType);
        return convertErrorDetailResponseService.convertToErrorDetailResponse(errorDetailRepository.save(errorDetail));
    }
}

