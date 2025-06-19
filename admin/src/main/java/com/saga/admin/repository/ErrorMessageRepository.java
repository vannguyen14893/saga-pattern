package com.saga.admin.repository;

import com.saga.admin.entity.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, Long> {
}
