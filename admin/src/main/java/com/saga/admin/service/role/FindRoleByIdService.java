package com.saga.admin.service.role;

import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.entity.Role;
import com.saga.admin.repository.RoleRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for finding Role entities by ID
 */
@Service
@RequiredArgsConstructor
public class FindRoleByIdService {
    private final RoleRepository roleRepository;

    private final ConvertRoleResponseService convertRoleResponseService;


    /**
     * Finds a Role by its ID
     *
     * @param id The ID of the role to find
     * @return The found role as a RoleResponse
     * @throws NotFoundExceptionHandler if role is not found
     */
    public RoleResponse findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Role"));
        return convertRoleResponseService.convertFromRole(role);
    }
}
