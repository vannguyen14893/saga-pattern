package com.saga.admin.service.errortype;

import com.saga.admin.dto.response.errortype.ErrorTypeResponse;
import com.saga.admin.repository.ErrorTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving lists of error types
 */
@Service
@RequiredArgsConstructor
public class GetListErrorTypeService {
    private final ErrorTypeRepository errorTypeRepository;
    private final ConvertErrorTypeResponseService convertErrorTypeResponseService;

    /**
     * Retrieves all error types and converts them to ErrorTypeResponse DTOs
     *
     * @return List of ErrorTypeResponse containing all error type data
     */
    public List<ErrorTypeResponse> findAll() {
        return errorTypeRepository.findAll().stream()
                .map(convertErrorTypeResponseService::convertToErrorTypeResponse)
                .toList();
    }
}

