package com.example.authorzation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInfoController {

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