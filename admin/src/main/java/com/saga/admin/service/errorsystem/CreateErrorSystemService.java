package com.saga.admin.service.errorsystem;

import com.saga.admin.dto.request.errorsystem.CreateErrorSystemRequest;
import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.entity.ErrorSystem;
import com.saga.admin.repository.ErrorSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to create a new ErrorSystem.
 */
@Service
@RequiredArgsConstructor
public class CreateErrorSystemService {
    private final ErrorSystemRepository errorSystemRepository;
    private final ConvertErrorSystemResponseService convertErrorSystemResponseService;

    @Transactional
    public ErrorSystemResponse create(CreateErrorSystemRequest request) {
        ErrorSystem errorSystem = new ErrorSystem();
        errorSystem.setName(request.getName());
        errorSystem.setDescription(request.getDescription());
        errorSystem.setSystemName(request.getSystemName());
        errorSystem = errorSystemRepository.save(errorSystem);
        return convertErrorSystemResponseService.convertToErrorSystemResponse(errorSystem);
    }
}

