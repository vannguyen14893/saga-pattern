package com.saga.admin.service.role;

import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for retrieving lists of Role entities
 */
@Service
@RequiredArgsConstructor
public class GetListRoleService {
    private final RoleRepository roleRepository;
    private final ConvertRoleResponseService convertRoleResponseService;

    /**
     * Retrieves all roles and converts them to RoleResponse DTOs
     *
     * @return List of RoleResponse DTOs representing all roles
     */
    public List<RoleResponse> findAll() {
        return roleRepository.findAll().stream().map(convertRoleResponseService::convertFromRole).toList();
    }

}
