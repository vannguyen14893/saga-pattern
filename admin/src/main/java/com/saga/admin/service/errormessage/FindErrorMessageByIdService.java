package com.saga.admin.service.errormessage;

import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.entity.ErrorMessage;
import com.saga.admin.repository.ErrorMessageRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for finding error messages by ID
 */
@Service
@RequiredArgsConstructor
public class FindErrorMessageByIdService {
    private final ErrorMessageRepository errorMessageRepository;
    private final ConvertErrorMessageResponseService convertErrorMessageResponseService;

    /**
     * Finds an error message by its ID and converts it to a ErrorMessageResponse
     *
     * @param id The ID of the error message to find
     * @return ErrorMessageResponse containing the found error message data
     * @throws NotFoundExceptionHandler if error message with given ID is not found
     */
    public ErrorMessageResponse findById(Long id) {
        ErrorMessage errorMessage = errorMessageRepository.findById(id)
            .orElseThrow(() -> new NotFoundExceptionHandler("ErrorMessage"));
        return convertErrorMessageResponseService.convertToErrorMessageResponse(errorMessage);
    }
}

