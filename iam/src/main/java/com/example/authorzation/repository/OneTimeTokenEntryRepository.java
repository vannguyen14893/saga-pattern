package com.example.authorzation.repository;

import com.example.authorzation.entity.OneTimeTokenEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneTimeTokenEntryRepository extends JpaRepository<OneTimeTokenEntry, String> {
    OneTimeTokenEntry findByTokenAndUsername(String token, String username);
}
