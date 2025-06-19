package com.saga.admin.service.errordetail;

import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.entity.ErrorDetail;
import com.saga.admin.repository.ErrorDetailRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for finding error details by ID
 */
@Service
@RequiredArgsConstructor
public class FindErrorDetailByIdService {
    private final ErrorDetailRepository errorDetailRepository;
    public final ConvertErrorDetailResponseService convertErrorDetailResponseService;

    /**
     * Finds an error detail by its ID and converts it to a ErrorDetailResponse
     *
     * @param id The ID of the error detail to find
     * @return ErrorDetailResponse containing the found error detail data
     * @throws NotFoundExceptionHandler if error detail with given ID is not found
     */
    public ErrorDetailResponse findById(Long id) {
        ErrorDetail errorDetail = errorDetailRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("ErrorDetail"));
        return convertErrorDetailResponseService.convertToErrorDetailResponse(errorDetail);
    }
}

