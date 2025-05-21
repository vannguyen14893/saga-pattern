package com.saga.admin.service.group;

import com.saga.admin.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for deleting groups
 */
@Service
@RequiredArgsConstructor
public class DeleteGroupService {
    private final GroupRepository groupRepository;

    /**
     * Deletes a group by its ID
     *
     * @param id The ID of the group to delete
     * @return The ID of the deleted group
     */
    public Long delete(Long id) {
        groupRepository.deleteById(id);
        return id;
    }
}