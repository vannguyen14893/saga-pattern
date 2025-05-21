package com.saga.admin.service.permission;

import com.saga.admin.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for deleting Permission entities
 */
@Service
@RequiredArgsConstructor
public class DeletePermissionService {
    private final PermissionRepository permissionRepository;

    /**
     * Deletes a permission by its ID
     *
     * @param id The ID of the permission to delete
     * @return The ID of the deleted permission
     */
    public Long delete(Long id) {
        permissionRepository.deleteById(id);
        return id;
    }

}