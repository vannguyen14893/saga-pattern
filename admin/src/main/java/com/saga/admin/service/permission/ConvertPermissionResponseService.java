package com.saga.admin.service.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.entity.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service to convert Permission entity to PermissionResponse DTO
 */
@Service
@RequiredArgsConstructor
public class ConvertPermissionResponseService {

    /**
     * Converts a Permission entity to PermissionResponse DTO
     *
     * @param permission The Permission entity to convert
     * @return The converted PermissionResponse DTO
     */
    public PermissionResponse convertToPermissionResponse(Permission permission) {
        return new PermissionResponse(permission.getId(), permission.getName(), permission.getDescription());
    }
}