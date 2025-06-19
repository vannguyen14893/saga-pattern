package com.saga.admin.repository;

import com.saga.admin.entity.ErrorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorCategoryRepository extends JpaRepository<ErrorCategory, Long> {
}
