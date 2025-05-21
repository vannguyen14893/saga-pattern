package com.saga.admin.service.role;

import com.saga.admin.dto.request.role.CreateRoleRequest;
import com.saga.admin.dto.request.role.UpdateRoleRequest;
import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.entity.Role;
import com.saga.admin.enums.GroupEnums;
import com.saga.admin.repository.GroupRepository;
import com.saga.admin.repository.RoleRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for creating Role entities
 */
@Service
@RequiredArgsConstructor
public class CreateRoleService {
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    private final ConvertRoleResponseService convertRoleResponseService;

    /**
     * Creates a new Role entity
     *
     * @param createRoleRequest The request containing role details
     * @return The created role as a RoleResponse
     */
    public RoleResponse create(CreateRoleRequest createRoleRequest) {
        Role role = new Role();
        role.setName(createRoleRequest.name());
        role.setDescription(createRoleRequest.description());
        Set<Group> groups = groupRepository.findAllByIdInAndType(createRoleRequest.groupIds(), GroupEnums.GROUP_ROLE.name()).collect(Collectors.toSet());
        role.setGroups(groups);
        return convertRoleResponseService.convertFromRole(roleRepository.save(role));
    }
}
