package com.example.authorzation.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) {
        log.info("login fail {}", e.getAuthentication().getName());
        //loginAttemptService.loginFail(e.getAuthentication().getName());
    }
}
