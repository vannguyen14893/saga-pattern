package com.saga.admin.service.group;

import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for converting Group entities to GroupResponse DTOs
 */
@Service
@RequiredArgsConstructor
public class ConvertGroupResponseService {

    /**
     * Converts a Group entity to GroupResponse DTO
     *
     * @param group The Group entity to convert
     * @return GroupResponse containing the group data
     */
    public GroupResponse convertToGroupResponse(Group group) {
        return new GroupResponse(group.getId(), group.getName(), group.getDescription(), group.getType());
    }
}