package com.example.authorzation.config;

import com.example.authorzation.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;


/**
 * Listener component that handles authentication failure events.
 * This listener is triggered when a user fails to authenticate,
 * and logs the failure attempt while updating the failure count
 * through the LoginService.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private final LoginService loginService;

    /**
     * Handles the authentication failure event by logging the failure
     * and updating the failed login attempts for the user.
     *
     * @param e the authentication failure event containing the failed authentication details
     */
    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) {
        log.info("login fail {}", e.getAuthentication().getName());
        loginService.loginFail(e.getAuthentication().getName());
    }
}
