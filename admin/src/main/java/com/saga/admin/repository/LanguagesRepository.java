package com.saga.admin.repository;

import com.saga.admin.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguagesRepository extends JpaRepository<Languages, Long> {
    List<Languages> findAllByActiveIsTrue();
}
