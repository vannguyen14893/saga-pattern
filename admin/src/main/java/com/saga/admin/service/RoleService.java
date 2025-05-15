package com.saga.admin.service;

import com.saga.admin.dto.request.role.CreateRoleRequest;
import com.saga.admin.dto.request.role.UpdateRoleRequest;
import com.saga.admin.dto.response.role.RoleResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.entity.Role;
import com.saga.admin.repository.GroupRepository;
import com.saga.admin.repository.PermissionRepository;
import com.saga.admin.repository.RoleRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    public List<RoleResponse> findAll() {
        return roleRepository.findAll().stream().map(this::convertFromRole).toList();
    }

    public RoleResponse findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Role"));
        return convertFromRole(role);
    }

    public RoleResponse create(CreateRoleRequest createRoleRequest) {
        Role role = new Role();
        role.setName(createRoleRequest.name());
        role.setDescription(createRoleRequest.description());
        Set<Group> groups = groupRepository.findAllByIdInAndType(createRoleRequest.groupIds(), "GROUP_ROLE").collect(Collectors.toSet());
        role.setGroups(groups);
        return convertFromRole(roleRepository.save(role));
    }

    public RoleResponse update(UpdateRoleRequest updateRoleRequest) {
        Role role = roleRepository.findById(updateRoleRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("Role"));
        role.setDescription(updateRoleRequest.description());
        role.getGroups().clear();
        Set<Group> groups = groupRepository.findAllByIdInAndType(updateRoleRequest.groupIds(), "GROUP_ROLE").collect(Collectors.toSet());
        role.setGroups(groups);
        return convertFromRole(roleRepository.save(role));
    }

    public Long delete(Long id) {
        roleRepository.deleteById(id);
        return id;
    }

    private RoleResponse convertFromRole(Role role) {
        return new RoleResponse(role.getId(), role.getName(), role.getDescription());
    }
}
