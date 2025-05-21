package com.saga.admin.repository;

import com.saga.admin.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

/**
 * Repository interface for managing Group entities.
 * Provides CRUD operations and custom queries for Group data access.
 */
public interface GroupRepository extends JpaRepository<Group, Long> {
    /**
     * Finds all groups with specified IDs and type.
     *
     * @param ids  list of group IDs to search for
     * @param type the type of groups to filter
     * @return a Stream of Group entities matching the criteria
     */
    @Transactional(readOnly = true)
    Stream<Group> findAllByIdInAndType(List<Long> ids, String type);
}
