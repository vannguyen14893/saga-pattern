package com.example.authorzation.controller;

import com.saga.exceptions.config.DBMessageSourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Controller handling user authentication and login-related functionality.
 * Provides endpoints for login page, authenticated user greeting, and public access.
 */

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final DBMessageSourceConfig dbMessageSourceConfig;

    /**
     * Displays the login page and handles login-related messages.
     *
     * @param message Optional message parameter to display on login page
     * @param code    Optional code parameter
     * @param model   Spring MVC model for view attributes
     * @return the view name for login page
     */
    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String message, @RequestParam(required = false) String code, Model model) {
        if (Strings.isNotBlank(message)) {
            model.addAttribute("message", dbMessageSourceConfig.getMessages(message));
        }
        return "login";
    }


    /**
     * Displays a personalized greeting for authenticated users.
     *
     * @param principal Security principal containing user information
     * @param model     Spring MVC model for view attributes
     * @return the view name for test page
     */
    @RequestMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("username", "Hello " + principal.getName());
        return "test";
    }

    /**
     * Displays a public greeting page accessible without authentication.
     *
     * @return the view name for test page
     */
    @RequestMapping("/hello-public")
    public String helloPublic() {
        return "test";
    }
}
