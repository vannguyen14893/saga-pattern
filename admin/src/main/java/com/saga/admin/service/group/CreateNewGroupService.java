package com.saga.admin.service.group;

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

/**
 * Service class for creating new groups
 */
@Service
@RequiredArgsConstructor
public class CreateNewGroupService {
    private final GroupRepository groupRepository;
    public final ConvertGroupResponseService convertGroupResponseService;

    /**
     * Creates a new group from the provided request
     *
     * @param createGroupRequest The request containing group details
     * @return GroupResponse containing the created group data
     */
    public GroupResponse create(CreateGroupRequest createGroupRequest) {
        Group group = new Group();
        group.setName(createGroupRequest.name());
        group.setDescription(createGroupRequest.description());
        group.setType(createGroupRequest.type());
        return convertGroupResponseService.convertToGroupResponse(groupRepository.save(group));
    }

}