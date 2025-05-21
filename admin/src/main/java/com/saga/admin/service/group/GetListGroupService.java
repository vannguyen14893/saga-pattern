package com.saga.admin.service.group;

import com.saga.admin.dto.response.group.GroupResponse;
import com.saga.admin.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving lists of groups
 */
@Service
@RequiredArgsConstructor
public class GetListGroupService {
    private final GroupRepository groupRepository;
    public final ConvertGroupResponseService convertGroupResponseService;

    /**
     * Retrieves all groups and converts them to GroupResponse DTOs
     *
     * @return List of GroupResponse containing all group data
     */
    public List<GroupResponse> findAll() {
        return groupRepository.findAll().stream().map(convertGroupResponseService::convertToGroupResponse).toList();
    }
}