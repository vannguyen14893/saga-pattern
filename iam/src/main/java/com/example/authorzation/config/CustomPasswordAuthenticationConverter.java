package com.example.authorzation.config;

import com.example.authorzation.service.CustomUserDetailCache;
import com.example.authorzation.service.OneTimeTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

/**
 * Custom authentication converter for handling password-based authentication requests.
 * This converter processes HTTP requests to extract authentication credentials and validates them
 * against registered clients and user details. It supports both regular password authentication
 * and one-time token validation for disabled users.
 */
@RequiredArgsConstructor
public class CustomPasswordAuthenticationConverter implements AuthenticationConverter {

    private final RegisteredClientRepository registeredClientRepository;
    private final CustomUserDetailCache customUserDetailCache;
    private final PasswordEncoder passwordEncoder;
    private final OneTimeTokenService oneTimeTokenService;

    /**
     * Converts an HTTP request into an Authentication object.
     * This method:
     * 1. Validates the client ID and grant type
     * 2. Extracts username and password from the request
     * 3. Validates the credentials against stored user details
     * 4. Handles special case for disabled users with one-time tokens
     *
     * @param request the HTTP request containing authentication parameters
     * @return CustomPasswordAuthenticationToken if authentication is successful
     * @throws OAuth2AuthenticationException if validation fails for any reason
     */
    @Override
    public Authentication convert(HttpServletRequest request) {
        String clientId = request.getParameter(OAuth2ParameterNames.CLIENT_ID);
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
        if (registeredClient == null) {
            throw new OAuth2AuthenticationException(new OAuth2Error("unsupported client id"));
        }
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (registeredClient.getAuthorizationGrantTypes().stream().filter(item -> item.getValue().equals(grantType)).isParallel()) {
            throw new OAuth2AuthenticationException(new OAuth2Error("unsupported_grant_type", "Grant type not supported: " + grantType, null));
        }
        String username = request.getParameter(OAuth2ParameterNames.USERNAME);
        String password = request.getParameter(OAuth2ParameterNames.PASSWORD);
        // Validate required parameters
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw new OAuth2AuthenticationException(new OAuth2Error("invalid_request"));
        }
        UserDetails userFromCache = customUserDetailCache.getUserFromCache(username);
        if (userFromCache == null) {
            throw new OAuth2AuthenticationException(new OAuth2Error("username not found", "username not found: " + username, "username not found: " + username));
        }
        if (passwordEncoder.matches(password, userFromCache.getPassword())) {
            return new CustomPasswordAuthenticationToken(username, password, null, registeredClient, grantType);
        } else if (!userFromCache.isEnabled()) {
            oneTimeTokenService.validateToken(password, username);
            return new CustomPasswordAuthenticationToken(username, password, null, registeredClient, grantType);
        } else
            throw new OAuth2AuthenticationException(new OAuth2Error("password invalid", "password invalid", null));
    }
}
