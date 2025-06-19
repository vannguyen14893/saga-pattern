package com.saga.admin.service.errorsystem;

import com.saga.admin.dto.request.errorsystem.UpdateErrorSystemRequest;
import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.entity.ErrorSystem;
import com.saga.admin.repository.ErrorSystemRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to update an existing ErrorSystem.
 */
@Service
@RequiredArgsConstructor
public class UpdateErrorSystemService {
    private final ErrorSystemRepository errorSystemRepository;
    private final ConvertErrorSystemResponseService convertErrorSystemResponseService;

    @Transactional
    public ErrorSystemResponse update(Long id, UpdateErrorSystemRequest request) {
        ErrorSystem errorSystem = errorSystemRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler("ErrorSystem"));
        errorSystem.setName(request.getName());
        errorSystem.setDescription(request.getDescription());
        errorSystem.setSystemName(request.getSystemName());
        errorSystem = errorSystemRepository.save(errorSystem);
        return convertErrorSystemResponseService.convertToErrorSystemResponse(errorSystem);
    }
}

