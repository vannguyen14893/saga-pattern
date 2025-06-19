package com.saga.admin.service.errormessage;

import com.saga.admin.dto.request.errormessage.CreateErrorMessageRequest;
import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.entity.ErrorMessage;
import com.saga.admin.entity.ErrorType;
import com.saga.admin.repository.ErrorMessageRepository;
import com.saga.admin.repository.ErrorTypeRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for creating new error messages
 */
@Service
@RequiredArgsConstructor
public class CreateErrorMessageService {
    private final ErrorMessageRepository errorMessageRepository;
    private final ErrorTypeRepository errorTypeRepository;
    private final ConvertErrorMessageResponseService convertErrorMessageResponseService;

    /**
     * Creates a new error message from the provided request
     *
     * @param request The request containing error message details
     * @return ErrorMessageResponse containing the created error message data
     */
    public ErrorMessageResponse create(CreateErrorMessageRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setLanguageCode(request.getLanguageCode());
        errorMessage.setText(request.getText());
        ErrorType errorType = errorTypeRepository.findById(request.getErrorTypeId()).orElseThrow(() -> new NotFoundExceptionHandler("ErrorType"));
        errorMessage.setErrorType(errorType);
        return convertErrorMessageResponseService.convertToErrorMessageResponse(errorMessageRepository.save(errorMessage));
    }
}

