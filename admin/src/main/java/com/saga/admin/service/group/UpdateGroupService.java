package com.saga.admin.service.group;

import com.saga.admin.dto.request.group.UpdateGroupRequest;
import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.repository.GroupRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for updating existing groups
 */
@Service
@RequiredArgsConstructor
public class UpdateGroupService {
    private final GroupRepository groupRepository;
    public final ConvertGroupResponseService convertGroupResponseService;

    /**
     * Updates an existing group with new data from the provided request
     *
     * @param updateGroupRequest The request containing updated group details
     * @return GroupResponse containing the updated group data
     * @throws NotFoundExceptionHandler if group with given ID is not found
     */
    public GroupResponse update(UpdateGroupRequest updateGroupRequest) {
        Group group = groupRepository.findById(updateGroupRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("Group"));
        group.setName(updateGroupRequest.name());
        group.setDescription(updateGroupRequest.description());
        return convertGroupResponseService.convertToGroupResponse(groupRepository.save(group));
    }

}