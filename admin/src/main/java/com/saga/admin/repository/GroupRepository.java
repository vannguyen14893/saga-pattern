package com.saga.admin.repository;

import com.saga.admin.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Transactional(readOnly = true)
    Stream<Group> findAllByIdInAndType(List<Long> ids, String type);
}
