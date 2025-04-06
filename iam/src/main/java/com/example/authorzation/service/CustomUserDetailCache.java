package com.example.authorzation.service;

import com.example.authorzation.entity.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailCache implements UserCache {

    private final Map<String, User> userDetails = new HashMap<>();

    @Override
    public UserDetails getUserFromCache(String username) {
        return userDetails.get(username);
    }

    @Override
    public void putUserInCache(UserDetails user) {
        userDetails.put(user.getUsername(), (User) user);

    }

    @Override
    public void removeUserFromCache(String username) {

    }

}
