package com.example.authorzation.repository;

import com.example.authorzation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);

    User findByEmail(String email);

}
