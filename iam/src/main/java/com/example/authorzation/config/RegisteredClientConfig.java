package com.example.authorzation.config;

import com.example.authorzation.entity.User;
import com.example.authorzation.service.DeviceCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContext;
import org.springframework.security.oauth2.server.authorization.token.*;

import java.time.Instant;

/**
 * Configuration class for OAuth2 client registration and token management.
 * Provides beans for client repository, token generation, authorization services,
 * and JWT token customization.
 */

@Configuration
@RequiredArgsConstructor
public class RegisteredClientConfig {
    private final JdbcTemplate jdbcTemplate;
    private final JWKSourceConfig jwkSourceConfig;

    /**
     * Creates a JDBC-based repository for managing registered OAuth2 clients.
     *
     * @return RegisteredClientRepository instance backed by JDBC storage
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    /**
     * Configures the OAuth2 token generator with JWT, access token, and refresh token generation capabilities.
     *
     * @return Combined OAuth2TokenGenerator for different token types
     */
    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator() {
        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSourceConfig.jwkSource());
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        jwtGenerator.setJwtCustomizer(jwtCustomizer());
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    /**
     * Creates a JDBC-based OAuth2 authorization service.
     *
     * @param registeredClientRepository Repository for client registration data
     * @return OAuth2AuthorizationService instance for managing authorizations
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * Creates a JDBC-based OAuth2 authorization consent service.
     *
     * @param registeredClientRepository Repository for client registration data
     * @return OAuth2AuthorizationConsentService instance for managing user consents
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(
            RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * Configures JWT token customization with additional claims.
     * Adds standard claims, client-specific claims, user-specific claims, and dynamic claims.
     *
     * @return OAuth2TokenCustomizer for JWT encoding context
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                // Add standard claims
                context.getClaims()
                        .issuer(context.getAuthorizationServerContext().getIssuer())
                        .claim("grant_type", context.getAuthorizationGrantType().getValue())
                        .claim("token_type", "Bearer")
                        .claim("version", "1.0");
                // Add custom claims from authentication
                Authentication principal = context.getPrincipal();
                if (principal instanceof OAuth2ClientAuthenticationToken) {
                    context.getClaims().claim("client_type", "confidential");
                }

                // Add user-specific claims
                if (principal instanceof UsernamePasswordAuthenticationToken) {
                    User user = (User) principal.getPrincipal();
                    context.getClaims().claim("user_id", user.getId());
                }

                // Add dynamic claims
                context.getClaims()
                        .claim("auth_time", Instant.now().getEpochSecond())
                        .claim("ip_address", getClientIp(context.getAuthorizationServerContext()));
            }
        };
    }

    /**
     * Retrieves the client IP address from the authorization context.
     *
     * @param context The authorization server context
     * @return String representation of the client IP address
     */
    private String getClientIp(AuthorizationServerContext context) {
        return "192.168.1.1";
    }
}
