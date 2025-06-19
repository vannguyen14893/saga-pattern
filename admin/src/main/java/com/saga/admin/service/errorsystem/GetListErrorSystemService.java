package com.saga.admin.service.errorsystem;

import com.saga.admin.dto.response.errorsystem.ErrorSystemResponse;
import com.saga.admin.repository.ErrorSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListErrorSystemService {
    private final ErrorSystemRepository errorSystemRepository;
    private final ConvertErrorSystemResponseService convertErrorSystemResponseService;

    public List<ErrorSystemResponse> findAll() {
        return errorSystemRepository.findAll().stream()
                .map(convertErrorSystemResponseService::convertToErrorSystemResponse)
                .toList();
    }
}

