package com.example.authorzation.controller;

import com.saga.exceptions.config.DBMessageSourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final DBMessageSourceConfig dbMessageSourceConfig;

    @RequestMapping(value = {"/login", "/"})
    public String login(@RequestParam(required = false) String message, @RequestParam(required = false) String code, Model model) throws IOException {

        if (Strings.isNotBlank(message)) {
            model.addAttribute("message", dbMessageSourceConfig.getMessages(message));
        }
        return "login";
    }

    @RequestMapping(value = "/login-fail")
    public String loginFail(@RequestParam(required = false) String message, Model model) {
        if (Strings.isNotBlank(message)) {
            if (message.equals("logout")) {
                model.addAttribute("message", "Logout!");
            }
            model.addAttribute("message", dbMessageSourceConfig.getMessages(message));
        }
        return "login";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "index";
    }
}
