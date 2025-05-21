package com.example.authorzation.service;

import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 * This service handles user authentication by loading user details from a cache
 * and performing additional validation checks. If a user is not found in the cache,
 * it throws an AuthenticationExceptionHandler.
 * <p>
 * The service uses:
 * - CustomUserDetailCache for retrieving cached user information
 * - CustomUserDetailsChecker for additional validation of user details
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final CustomUserDetailCache customUserDetailCache;
    private final CustomUserDetailsChecker customUserDetailsChecker;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails userDetails = customUserDetailCache.getUserFromCache(username);
        if (userDetails == null) throw new AuthenticationExceptionHandler("user_not_found");
        customUserDetailsChecker.check(userDetails);
        return userDetails;
    }
}
