package com.saga.admin.service.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.dto.response.permission.UpdatePermissionRequest;
import com.saga.admin.entity.Permission;
import com.saga.admin.repository.PermissionRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for updating Permission entities
 */
@Service
@RequiredArgsConstructor
public class UpdatePermissionService {
    private final PermissionRepository permissionRepository;
    private final ConvertPermissionResponseService convertPermissionResponseService;


    /**
     * Updates an existing Permission entity
     *
     * @param updatePermissionRequest The request containing updated permission details
     * @return The updated permission as a PermissionResponse
     * @throws NotFoundExceptionHandler if permission is not found
     */
    public PermissionResponse update(UpdatePermissionRequest updatePermissionRequest) {
        Permission permission = permissionRepository.findById(updatePermissionRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("Permission"));
        permission.setName(updatePermissionRequest.name());
        permission.setDescription(updatePermissionRequest.description());
        return convertPermissionResponseService.convertToPermissionResponse(permissionRepository.save(permission));
    }
}
