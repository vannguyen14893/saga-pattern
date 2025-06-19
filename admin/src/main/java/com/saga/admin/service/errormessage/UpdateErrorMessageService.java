package com.saga.admin.service.errormessage;

import com.saga.admin.dto.request.errormessage.UpdateErrorMessageRequest;
import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.entity.ErrorMessage;
import com.saga.admin.entity.ErrorType;
import com.saga.admin.repository.ErrorMessageRepository;
import com.saga.admin.repository.ErrorTypeRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for updating existing error messages
 */
@Service
@RequiredArgsConstructor
public class UpdateErrorMessageService {
    private final ErrorMessageRepository errorMessageRepository;
    private final ErrorTypeRepository errorTypeRepository;
    private final ConvertErrorMessageResponseService convertErrorMessageResponseService;

    /**
     * Updates an existing error message with new data from the provided request
     *
     * @param request The request containing updated error message details
     * @return ErrorMessageResponse containing the updated error message data
     * @throws NotFoundExceptionHandler if error message with given ID is not found
     */
    public ErrorMessageResponse update(UpdateErrorMessageRequest request) {
        ErrorMessage errorMessage = errorMessageRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundExceptionHandler("ErrorMessage"));
        errorMessage.setLanguageCode(request.getLanguageCode());
        errorMessage.setText(request.getText());
        ErrorType errorType = errorTypeRepository.findById(request.getErrorTypeId()).orElseThrow(() -> new NotFoundExceptionHandler("ErrorType"));
        errorMessage.setErrorType(errorType);
        return convertErrorMessageResponseService.convertToErrorMessageResponse(errorMessageRepository.save(errorMessage));
    }
}
