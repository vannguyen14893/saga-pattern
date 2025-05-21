package com.example.authorzation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for providing user information endpoints.
 * Handles requests for retrieving authenticated user details.
 */
@RestController
public class UserInfoController {

    /**
     * Retrieves information about the currently authenticated user.
     *
     * @param principal the authenticated user's principal
     * @return a map containing user information including subject, email, name, and preferred username
     */
    @GetMapping("/userinfo")
    public Map<String, Object> getUserInfo(Principal principal) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("sub", principal.getName());
        userInfo.put("email", "email");
        userInfo.put("name", principal.getName());
        userInfo.put("preferred_username", "preferred_username");
        return userInfo;
    }
}