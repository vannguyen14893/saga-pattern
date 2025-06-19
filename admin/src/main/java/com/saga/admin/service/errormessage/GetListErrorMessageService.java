package com.saga.admin.service.errormessage;

import com.saga.admin.dto.response.errormessage.ErrorMessageResponse;
import com.saga.admin.repository.ErrorMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving lists of error messages
 */
@Service
@RequiredArgsConstructor
public class GetListErrorMessageService {
    private final ErrorMessageRepository errorMessageRepository;
    private final ConvertErrorMessageResponseService convertErrorMessageResponseService;

    /**
     * Retrieves all error messages and converts them to ErrorMessageResponse DTOs
     *
     * @return List of ErrorMessageResponse containing all error message data
     */
    public List<ErrorMessageResponse> findAll() {
        return errorMessageRepository.findAll().stream()
                .map(convertErrorMessageResponseService::convertToErrorMessageResponse)
                .toList();
    }
}

