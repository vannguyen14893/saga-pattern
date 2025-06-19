package com.saga.admin.repository;

import com.saga.admin.entity.ErrorDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorDetailRepository extends JpaRepository<ErrorDetail, Long> {
}
