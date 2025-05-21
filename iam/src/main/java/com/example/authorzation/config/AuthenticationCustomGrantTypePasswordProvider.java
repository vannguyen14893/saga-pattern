package com.example.authorzation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

/**
 * Custom authentication provider that handles password grant type authentication for OAuth2.
 * This provider generates and manages access tokens and refresh tokens for authenticated users.
 * It works with the OAuth2 authorization framework to provide secure token-based authentication.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationCustomGrantTypePasswordProvider implements AuthenticationProvider {
    private final OAuth2TokenGenerator<?> tokenGenerator;
    private final OAuth2AuthorizationService authorizationService;


    /**
     * Authenticates the provided authentication token and generates OAuth2 tokens.
     * This method processes the custom password authentication token, generates access and refresh tokens,
     * saves the authorization, and returns the authenticated token with additional parameters.
     *
     * @param authentication the authentication token to process
     * @return authenticated OAuth2AccessTokenAuthenticationToken containing access and refresh tokens
     * @throws AuthenticationException if authentication fails or token generation fails
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomPasswordAuthenticationToken customPasswordAuthenticationToken = (CustomPasswordAuthenticationToken) authentication;
        RegisteredClient registeredClient = customPasswordAuthenticationToken.getRegisteredClient();
        String grantType = customPasswordAuthenticationToken.getGrantType();
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(authentication.getName())
                .authorizationGrantType(new AuthorizationGrantType(grantType))
                .attribute(registeredClient.getClientId(), authentication.getName());
        new HashSet<>(registeredClient.getScopes());
        // Generate tokens
        OAuth2TokenContext tokenContext = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(authentication)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizationGrantType(new AuthorizationGrantType(grantType))
                .authorizedScopes(registeredClient.getScopes())
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .build();

        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.SERVER_ERROR);
        }

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
        additionalParameter.put("username", customPasswordAuthenticationToken.getUsername());
        // Return the authenticated token
        return new OAuth2AccessTokenAuthenticationToken(
                registeredClient,
                authentication,
                accessToken,
                Objects.requireNonNull(authorization.getRefreshToken()).getToken(),
                additionalParameter);
    }

    /**
     * Determines if this provider supports the specified authentication token type.
     *
     * @param authentication the class to check
     * @return true if the class is assignable from CustomPasswordAuthenticationToken
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return CustomPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}