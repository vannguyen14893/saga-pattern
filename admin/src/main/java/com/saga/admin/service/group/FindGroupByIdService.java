package com.saga.admin.service.group;

import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.repository.GroupRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for finding groups by ID
 */
@Service
@RequiredArgsConstructor
public class FindGroupByIdService {
    private final GroupRepository groupRepository;
    public final ConvertGroupResponseService convertGroupResponseService;

    /**
     * Finds a group by its ID and converts it to a GroupResponse
     *
     * @param id The ID of the group to find
     * @return GroupResponse containing the found group data
     * @throws NotFoundExceptionHandler if group with given ID is not found
     */
    public GroupResponse findById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Group"));
        return convertGroupResponseService.convertToGroupResponse(group);
    }
}