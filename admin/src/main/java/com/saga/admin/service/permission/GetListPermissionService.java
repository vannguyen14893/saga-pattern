package com.saga.admin.service.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for retrieving list of all Permission entities
 */
@Service
@RequiredArgsConstructor
public class GetListPermissionService {
    private final PermissionRepository permissionRepository;
    private final ConvertPermissionResponseService convertPermissionResponseService;

    /**
     * Retrieves all permissions from the repository and converts them to PermissionResponse DTOs
     *
     * @return List of all permissions as PermissionResponse DTOs
     */
    public List<PermissionResponse> findAll() {
        return permissionRepository.findAll().stream().map(convertPermissionResponseService::convertToPermissionResponse).toList();
    }
}
