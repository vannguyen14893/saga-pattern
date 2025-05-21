package com.saga.admin.service.group;

import com.saga.admin.entity.Group;
import com.saga.admin.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for finding groups by type and ID list
 */
@Service
@RequiredArgsConstructor
public class FindGroupByTypeAndIdInService {
    private final GroupRepository groupRepository;

    /**
     * Finds all groups that match the given IDs and type
     *
     * @param groupId List of group IDs to find
     * @param type    The type of groups to find
     * @return Set of Group entities matching the criteria
     */
    public Set<Group> findAllByTypeAndIdIn(List<Long> groupId, String type) {
        return groupRepository.findAllByIdInAndType(groupId, type).collect(Collectors.toSet());
    }
}