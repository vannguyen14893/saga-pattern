package com.example.authorzation.config;

import com.example.authorzation.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

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
