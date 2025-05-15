package com.saga.admin.service;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.dto.response.permission.CreatePermissionRequest;
import com.saga.admin.dto.response.permission.UpdatePermissionRequest;
import com.saga.admin.entity.Permission;
import com.saga.admin.repository.PermissionRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public List<PermissionResponse> findAll() {
        return permissionRepository.findAll().stream().map(this::convertToPermissionResponse).toList();
    }

    public PermissionResponse findById(Long id) {
        return convertToPermissionResponse(permissionRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Permission")));
    }

    public PermissionResponse create(CreatePermissionRequest createPermissionRequest) {
        Permission permission = new Permission();
        permission.setName(createPermissionRequest.name());
        permission.setDescription(createPermissionRequest.description());
        return convertToPermissionResponse(permission);
    }

    public PermissionResponse update(UpdatePermissionRequest updatePermissionRequest) {
        Permission permission = permissionRepository.findById(updatePermissionRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("Permission"));
        permission.setName(updatePermissionRequest.name());
        permission.setDescription(updatePermissionRequest.description());
        return convertToPermissionResponse(permissionRepository.save(permission));
    }

    public Long delete(Long id) {
        permissionRepository.deleteById(id);
        return id;
    }

    private PermissionResponse convertToPermissionResponse(Permission permission) {
        return new PermissionResponse(permission.getId(), permission.getName(), permission.getDescription());
    }
}
