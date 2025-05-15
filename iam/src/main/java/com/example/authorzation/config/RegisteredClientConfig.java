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

@Configuration
@RequiredArgsConstructor
public class RegisteredClientConfig {
    private final JdbcTemplate jdbcTemplate;
    private final JWKSourceConfig jwkSourceConfig;

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator() {
        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSourceConfig.jwkSource());
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        jwtGenerator.setJwtCustomizer(jwtCustomizer());
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    @Bean
    public OAuth2AuthorizationService authorizationService(RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(
            RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

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

    private String getClientIp(AuthorizationServerContext context) {
        return "192.168.1.1";
    }
}
