package com.example.authorzation.config;

import com.example.authorzation.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Listener component that handles successful authentication events.
 * This listener is triggered when a user successfully authenticates,
 * and logs the successful login attempt. It can be extended to track
 * successful logins through the LoginService.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final LoginService loginService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
       // loginService.loginSuccess(event.getAuthentication().getName());
        log.info("login success ------------");
    }
}
