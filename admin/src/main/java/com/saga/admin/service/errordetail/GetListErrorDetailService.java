package com.saga.admin.service.errordetail;

import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.entity.ErrorDetail;
import com.saga.admin.repository.ErrorDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetListErrorDetailService {
    private final ErrorDetailRepository errorDetailRepository;
    private final ConvertErrorDetailResponseService convertErrorDetailResponseService;

    public List<ErrorDetailResponse> findAll() {
        return errorDetailRepository.findAll().stream()
                .map(convertErrorDetailResponseService::convertToErrorDetailResponse)
                .collect(Collectors.toList());
    }
}

