package com.saga.admin.dto.response.menu;

import java.util.Map;

public record MenuResponse(Long id,
                           String key,
                           String path,
                           String icon,
                           Integer order,
                           Long parentId, Map<String, String> translations) {
}
