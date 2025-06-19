package com.saga.admin.service.errortype;

import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.entity.ErrorType;
import com.saga.admin.repository.ErrorTypeRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for finding error types by ID
 */
@Service
@RequiredArgsConstructor
public class FindErrorTypeByIdService {
    private final ErrorTypeRepository errorTypeRepository;
    private final ConvertErrorTypeResponseService convertErrorTypeResponseService;

    /**
     * Finds an error type by its ID and converts it to a ErrorTypeResponse
     *
     * @param id The ID of the error type to find
     * @return ErrorTypeResponse containing the found error type data
     * @throws NotFoundExceptionHandler if error type with given ID is not found
     */
    public ErrorTypeResponse findById(Long id) {
        ErrorType errorType = errorTypeRepository.findById(id)
            .orElseThrow(() -> new NotFoundExceptionHandler("ErrorType"));
        return convertErrorTypeResponseService.convertToErrorTypeResponse(errorType);
    }
}

