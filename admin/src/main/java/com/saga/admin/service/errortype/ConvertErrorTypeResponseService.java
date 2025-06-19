package com.saga.admin.service.errortype;

import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.entity.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for converting ErrorType entities to ErrorTypeResponse DTOs
 */
@Service
@RequiredArgsConstructor
public class ConvertErrorTypeResponseService {
    /**
     * Converts an ErrorType entity to ErrorTypeResponse DTO
     *
     * @param errorType The ErrorType entity to convert
     * @return ErrorTypeResponse containing the error type data
     */
    public ErrorTypeResponse convertToErrorTypeResponse(ErrorType errorType) {
        return new ErrorTypeResponse(errorType.getId(), errorType.getName(), errorType.getBaseCode(),
                errorType.getHttpStatus(),errorType.getDeprecated(),errorType.getCategory().getId());
    }
}

