package com.saga.admin.service.role;

import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service to convert Role entity to RoleResponse DTO
 */
@Service
@RequiredArgsConstructor
public class ConvertRoleResponseService {
    /**
     * Converts a Role entity to RoleResponse DTO
     *
     * @param role The Role entity to convert
     * @return The converted RoleResponse DTO
     */
    public RoleResponse convertFromRole(Role role) {
        return new RoleResponse(role.getId(), role.getName(), role.getDescription());
    }
}
