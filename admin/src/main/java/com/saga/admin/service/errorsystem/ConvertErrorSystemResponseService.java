package com.saga.admin.service.errorsystem;

import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.entity.ErrorCategory;
import com.saga.admin.entity.ErrorSystem;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Service to convert ErrorSystem entity to ErrorSystemResponse DTO.
 */
@Service
public class ConvertErrorSystemResponseService {
    public ErrorSystemResponse convertToErrorSystemResponse(ErrorSystem errorSystem) {
        ErrorSystemResponse response = new ErrorSystemResponse();
        response.setId(errorSystem.getId());
        response.setName(errorSystem.getName());
        response.setDescription(errorSystem.getDescription());
        response.setSystemName(errorSystem.getSystemName());
        return response;
    }
}
