package com.saga.admin.service.role;

import com.saga.admin.dto.request.role.UpdateRoleRequest;
import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.entity.Role;
import com.saga.admin.enums.GroupEnums;
import com.saga.admin.repository.GroupRepository;
import com.saga.admin.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for updating Role entities
 */
@Service
@RequiredArgsConstructor
public class UpdateRoleService {
    private final FindRoleByIdService findRoleByIdService;
    private final GroupRepository groupRepository;
    private final ConvertRoleResponseService convertRoleResponseService;
    private final RoleRepository roleRepository;

    /**
     * Updates an existing Role entity with new information
     *
     * @param updateRoleRequest The request containing updated role details
     * @return The updated role as a RoleResponse
     */
    public RoleResponse update(UpdateRoleRequest updateRoleRequest) {
        RoleResponse roleResponse = findRoleByIdService.findById(updateRoleRequest.id());
        Role role = new Role();
        role.setId(roleResponse.id());
        role.setDescription(updateRoleRequest.description());
        role.getGroups().clear();
        Set<Group> groups = groupRepository.findAllByIdInAndType(updateRoleRequest.groupIds(), GroupEnums.GROUP_ROLE.name()).collect(Collectors.toSet());
        role.setGroups(groups);
        return convertRoleResponseService.convertFromRole(roleRepository.save(role));
    }


}
