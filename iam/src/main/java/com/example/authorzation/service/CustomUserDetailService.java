package com.example.authorzation.service;

import com.example.authorzation.exceptions.UserDetailNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final CustomUserDetailCache customUserDetailCache;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails userFromCache = customUserDetailCache.getUserFromCache(username);
        if (userFromCache == null) throw new UserDetailNotFound("user_not_found");
        return userFromCache;
    }
}
