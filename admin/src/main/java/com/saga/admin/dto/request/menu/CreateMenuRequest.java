package com.saga.admin.dto.request.menu;

import com.saga.admin.dto.request.translate.TranslateRequest;

import java.util.List;

public record CreateMenuRequest(
        String key,
        String path,
        String icon,
        Integer order,
        Long parentId,
        List<TranslateRequest> translates,
        List<Long> groupIds
        ) {
}
