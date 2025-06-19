package com.saga.admin.service.errormessage;

import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.entity.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for converting ErrorMessage entities to ErrorMessageResponse DTOs
 */
@Service
@RequiredArgsConstructor
public class ConvertErrorMessageResponseService {
    /**
     * Converts an ErrorMessage entity to ErrorMessageResponse DTO
     *
     * @param errorMessage The ErrorMessage entity to convert
     * @return ErrorMessageResponse containing the error message data
     */
    public ErrorMessageResponse convertToErrorMessageResponse(ErrorMessage errorMessage) {
        return new ErrorMessageResponse(errorMessage.getId(), errorMessage.getLanguageCode(), errorMessage.getText());
    }
}

