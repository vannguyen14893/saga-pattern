package com.example.authorzation.dto;


import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;

public class CustomTokenType {
    public static final OAuth2TokenType CUSTOM_PASSWORD_GRANT = new OAuth2TokenType("custom_password");
    public static final OAuth2TokenType CUSTOM_REFRESH_TOKEN_GRANT = new OAuth2TokenType("custom_refresh_token");
    public static final OAuth2TokenType CUSTOM_SMS_GRANT = new OAuth2TokenType("custom_sms");
    // Custom token types
    public static final OAuth2TokenType CUSTOM_EMAIL_GRANT = new OAuth2TokenType("custom_email");
    public static final OAuth2TokenType CUSTOM_REGIS_GRANT = new OAuth2TokenType("custom_regis");
    public static final OAuth2TokenType CUSTOM_GOOGLE_AUTH_GRANT = new OAuth2TokenType("custom_google_auth");

}
