package com.saga.admin.service.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.dto.response.permission.CreatePermissionRequest;
import com.saga.admin.dto.response.permission.UpdatePermissionRequest;
import com.saga.admin.entity.Permission;
import com.saga.admin.repository.PermissionRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for creating Permission entities
 */
@Service
@RequiredArgsConstructor
public class CreatePermissionService {
    private final PermissionRepository permissionRepository;
    private final ConvertPermissionResponseService convertPermissionResponseService;

    /**
     * Creates a new Permission entity
     *
     * @param createPermissionRequest The request containing permission details
     * @return The created permission as a PermissionResponse
     */
    public PermissionResponse create(CreatePermissionRequest createPermissionRequest) {
        Permission permission = new Permission();
        permission.setName(createPermissionRequest.name());
        permission.setDescription(createPermissionRequest.description());

        return convertPermissionResponseService.convertToPermissionResponse(permissionRepository.save(permission));
    }

}