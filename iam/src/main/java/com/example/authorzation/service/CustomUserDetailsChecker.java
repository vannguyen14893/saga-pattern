package com.example.authorzation.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails toCheck) {
        if (!toCheck.isEnabled()) {
            throw new RuntimeException("enable");
        }
        if (!toCheck.isAccountNonExpired()) {
            throw new RuntimeException("expired");
        }
        if (!toCheck.isAccountNonLocked()) {
            throw new RuntimeException("locked");
        }
    }
}
