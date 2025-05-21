package com.example.authorzation.service;

import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of UserDetailsChecker that validates user account status.
 * This service checks if user accounts are expired or locked before allowing authentication.
 */
@Service
public class CustomUserDetailsChecker implements UserDetailsChecker {

    /**
     * Validates the status of a user account.
     * Checks if the account is non-expired and non-locked.
     *
     * @param toCheck the UserDetails object to validate
     * @throws AuthenticationExceptionHandler if the account is expired or locked
     */
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
