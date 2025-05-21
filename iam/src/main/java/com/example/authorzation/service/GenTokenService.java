package com.example.authorzation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContext;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

/**
 * Service class responsible for generating OAuth2 access and refresh tokens.
 * This service handles token generation, authorization building, and token persistence
 * for the OAuth2 authorization flow.
 */
@RequiredArgsConstructor
@Service
public class GenTokenService {

    /**
     * Generator for creating OAuth2 tokens
     */
    private final OAuth2TokenGenerator<?> tokenGenerator;

    /**
     * Service for managing OAuth2 authorizations
     */
    private final OAuth2AuthorizationService authorizationService;

    /**
     * Settings for the authorization server configuration
     */
    private final AuthorizationServerSettings authorizationServerSettings;

    /**
     * Generates OAuth2 access and refresh tokens for a given client and authentication.
     *
     * @param registeredClient The registered OAuth2 client requesting the token
     * @param authentication   The authentication token containing user credentials
     * @param grantType        The OAuth2 grant type being used
     * @param username         The username of the authenticating user
     * @param tokenType        The type of token to generate
     * @return OAuth2AccessTokenAuthenticationToken containing the generated access and refresh tokens
     */
    public OAuth2AccessTokenAuthenticationToken generateToken(RegisteredClient registeredClient, UsernamePasswordAuthenticationToken authentication,
                                                              String grantType, String username,OAuth2TokenType tokenType) {
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization
                .withRegisteredClient(registeredClient)
                .principalName(authentication.getName())
                .authorizationGrantType(new AuthorizationGrantType(grantType))
                .attribute(registeredClient.getClientId(), authentication.getName());
        new HashSet<>(registeredClient.getScopes());
        // Generate tokens
        AuthorizationServerContext context = new AuthorizationServerContext() {
            @Override
            public String getIssuer() {
                return authorizationServerSettings.getIssuer();
            }

            @Override
            public AuthorizationServerSettings getAuthorizationServerSettings() {
                return authorizationServerSettings;
            }
        };
        AuthorizationServerContextHolder.setContext(context);
        OAuth2TokenContext tokenContext = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(authentication)
                .authorizationServerContext(context)
                .authorizationGrantType(new AuthorizationGrantType(grantType))
                .authorizedScopes(registeredClient.getScopes())
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .build();

        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        OAuth2AccessToken accessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(),
                generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(),
                tokenContext.getAuthorizedScopes());
        authorizationBuilder.token(accessToken);
        // Generate refresh token if supported
        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN)) {
            tokenContext = DefaultOAuth2TokenContext.builder()
                    .registeredClient(registeredClient)
                    .principal(authentication)
                    .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                    .authorizationGrantType(new AuthorizationGrantType(grantType))
                    .authorizedScopes(registeredClient.getScopes())
                    .tokenType(OAuth2TokenType.REFRESH_TOKEN)
                    .build();

            OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
            if (generatedRefreshToken != null) {
                OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                        generatedRefreshToken.getTokenValue(),
                        generatedRefreshToken.getIssuedAt(),
                        generatedRefreshToken.getExpiresAt());
                authorizationBuilder.token(refreshToken);
            }
        }
        // Save authorization
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);
        Map<String, Object> additionalParameter = new HashMap<>();
        additionalParameter.put("username", username);
        return new OAuth2AccessTokenAuthenticationToken(
                registeredClient,
                authentication,
                accessToken,
                Objects.requireNonNull(authorization.getRefreshToken()).getToken(),
                additionalParameter);
    }
}
