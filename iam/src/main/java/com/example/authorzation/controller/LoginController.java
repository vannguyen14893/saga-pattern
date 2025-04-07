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

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final DBMessageSourceConfig dbMessageSourceConfig;

    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String message, @RequestParam(required = false) String code, Model model) {
        if (Strings.isNotBlank(message)) {
            model.addAttribute("message", dbMessageSourceConfig.getMessages(message));
        }
        return "login";
    }


    @RequestMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("username", "Hello " + principal.getName());
        return "test";
    }

    @RequestMapping("/hello-public")
    public String helloPublic() {
        return "test";
    }
}
