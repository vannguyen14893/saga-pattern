package com.example.authorzation.config;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Collections;

@Getter
public class CustomPasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;
    private final String password;
    private final String customParam;
    private final RegisteredClient registeredClient;
    private final String grantType;

    public CustomPasswordAuthenticationToken(String username, String password, String customParam, RegisteredClient registeredClient, String grantType) {
        super(Collections.emptyList());
        this.username = username;
        this.password = password;
        this.customParam = customParam;
        this.registeredClient = registeredClient;
        this.grantType = grantType;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

}
