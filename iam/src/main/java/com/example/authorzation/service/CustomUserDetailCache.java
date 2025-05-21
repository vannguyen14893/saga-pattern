package com.example.authorzation.service;

import com.example.authorzation.entity.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom implementation of Spring Security's UserCache interface for caching user details.
 * This service maintains an in-memory cache of User objects using a HashMap for quick access
 * to user information without hitting the database for every authentication request.
 */
@Service
public class CustomUserDetailCache implements UserCache {

    private final Map<String, User> userDetails = new HashMap<>();

    /**
     * Retrieves a cached user from the internal map by username.
     *
     * @param username the username to look up
     * @return the cached UserDetails object, or null if not found
     */
    @Override
    public UserDetails getUserFromCache(String username) {
        return userDetails.get(username);
    }

    /**
     * Stores a user object in the cache.
     * Casts the UserDetails to User before storing in the internal map.
     *
     * @param user the UserDetails object to cache
     */
    @Override
    public void putUserInCache(UserDetails user) {
        userDetails.put(user.getUsername(), (User) user);

    }

    /**
     * Removes a user from the cache by username.
     *
     * @param username the username of the user to remove
     */
    @Override
    public void removeUserFromCache(String username) {

    }

}
