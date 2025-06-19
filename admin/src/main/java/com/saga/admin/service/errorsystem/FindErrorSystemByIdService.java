package com.saga.admin.service.errorsystem;

import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.entity.ErrorSystem;
import com.saga.admin.repository.ErrorSystemRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to find ErrorSystem by id and convert to response DTO.
 */
@Service
@RequiredArgsConstructor
public class FindErrorSystemByIdService {
    private final ErrorSystemRepository errorSystemRepository;
    private final ConvertErrorSystemResponseService convertErrorSystemResponseService;

    @Transactional(readOnly = true)
    public ErrorSystemResponse findById(Long id) {
        ErrorSystem errorSystem = errorSystemRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler("ErrorSystem"));
        return convertErrorSystemResponseService.convertToErrorSystemResponse(errorSystem);
    }
}

