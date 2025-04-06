package com.example.authorzation.service;

import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails toCheck) {
//        if (!toCheck.isEnabled()) {
//            throw new AuthenticationExceptionHandler("enable");
//        }
        if (!toCheck.isAccountNonExpired()) {
            throw new AuthenticationExceptionHandler("expired");
        }
        if (!toCheck.isAccountNonLocked()) {
            throw new AuthenticationExceptionHandler("locked");
        }
    }
}
