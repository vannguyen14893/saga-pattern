package com.saga.admin.service;

import com.saga.admin.dto.request.group.CreateGroupRequest;
import com.saga.admin.dto.request.group.UpdateGroupRequest;
import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.repository.GroupRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<GroupResponse> findAll() {
        return groupRepository.findAll().stream().map(this::convertToGroupResponse).toList();
    }

    public GroupResponse findById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Group"));
        return convertToGroupResponse(group);
    }

    public GroupResponse create(CreateGroupRequest createGroupRequest) {
        Group group = new Group();
        group.setName(createGroupRequest.name());
        group.setDescription(createGroupRequest.description());
        group.setType(createGroupRequest.type());
        return convertToGroupResponse(groupRepository.save(group));
    }

    public GroupResponse update(UpdateGroupRequest updateGroupRequest) {
        Group group = groupRepository.findById(updateGroupRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("Group"));
        group.setName(updateGroupRequest.name());
        group.setDescription(updateGroupRequest.description());
        return convertToGroupResponse(groupRepository.save(group));
    }

    public Long delete(Long id) {
        groupRepository.deleteById(id);
        return id;
    }

    public Set<Group> findAllByTypeAndIdIn(List<Long> groupId, String type) {
        return groupRepository.findAllByIdInAndType(groupId, type).collect(Collectors.toSet());
    }

    private GroupResponse convertToGroupResponse(Group group) {
        return new GroupResponse(group.getId(), group.getName(), group.getDescription(), group.getType());
    }
}
