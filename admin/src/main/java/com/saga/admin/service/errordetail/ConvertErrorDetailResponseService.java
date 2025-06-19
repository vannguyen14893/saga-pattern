package com.saga.admin.service.errordetail;

import com.saga.admin.dto.response.errordetail.ErrorDetailResponse;
import com.saga.admin.entity.ErrorDetail;
import org.springframework.stereotype.Service;

@Service
public class ConvertErrorDetailResponseService {
    public ErrorDetailResponse convertToErrorDetailResponse(ErrorDetail errorDetail) {
        ErrorDetailResponse response = new ErrorDetailResponse();
        response.setId(errorDetail.getId());
        response.setKey(errorDetail.getKey());
        response.setDescription(errorDetail.getDescription());
        response.setRequired(errorDetail.getRequired());
        response.setExampleValue(errorDetail.getExampleValue());
        return response;
    }
}

