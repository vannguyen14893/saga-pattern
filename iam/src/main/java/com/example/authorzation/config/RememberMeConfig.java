package com.example.authorzation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * Configuration class for remember-me authentication services.
 * Provides beans for persistent token-based remember-me functionality
 * using SHA256 for token generation and MD5 for token matching.
 */
@Configuration
public class RememberMeConfig {
    /**
     * Creates a RememberMeServices bean configured with token-based persistence.
     *
     * @param userDetailsService The user details service for authentication
     * @return TokenBasedRememberMeServices configured with SHA256 encoding and MD5 matching
     */
    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("remember-me", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.MD5);
        rememberMe.setTokenValiditySeconds(1000);
        return rememberMe;
    }
}
