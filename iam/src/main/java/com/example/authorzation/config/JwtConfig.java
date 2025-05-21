package com.example.authorzation.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;

/**
 * Configuration class for JWT decoding setup.
 * Provides a bean for JWT token validation using JWK source.
 */
@Configuration
public class JwtConfig {
    /**
     * Creates a JwtDecoder bean configured with the provided JWK source.
     *
     * @param jwkSource The JSON Web Key source used for JWT validation
     * @return JwtDecoder instance for token validation
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
}
