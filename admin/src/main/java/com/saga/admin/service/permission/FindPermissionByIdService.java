package com.saga.admin.service.permission;

import com.saga.admin.dto.request.permission.PermissionResponse;
import com.saga.admin.repository.PermissionRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for finding Permission entities by ID
 */
@Service
@RequiredArgsConstructor
public class FindPermissionByIdService {
    private final PermissionRepository permissionRepository;
    private final ConvertPermissionResponseService convertPermissionResponseService;

    /**
     * Finds a Permission by its ID
     *
     * @param id The ID of the permission to find
     * @return The found permission as a PermissionResponse
     * @throws NotFoundExceptionHandler if permission is not found
     */
    public PermissionResponse findById(Long id) {
        return convertPermissionResponseService.convertToPermissionResponse(permissionRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Permission")));
    }

}