package com.saga.admin.repository;

import com.saga.admin.entity.ErrorSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorSystemRepository extends JpaRepository<ErrorSystem, Long> {
}
