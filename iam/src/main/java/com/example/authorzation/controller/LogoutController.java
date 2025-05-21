package com.example.authorzation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller handling user logout functionality.
 * Provides endpoint for user logout operations.
 */
@Controller
public class LogoutController {
    /**
     * Handles user logout requests and redirects to login page.
     *
     * @return the view name for login page
     */
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
