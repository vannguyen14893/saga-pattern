package com.example.authorzation.config;

import com.example.authorzation.service.CustomUserDetailService;
import com.example.authorzation.service.OneTimeTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Custom authentication provider that handles username/password authentication
 * and one-time token validation. It works with both regular password authentication
 * and validates one-time tokens for users who are not yet enabled.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationCustomProvider implements AuthenticationProvider {
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final OneTimeTokenService oneTimeTokenService;

    /**
     * Authenticates the user using either password or one-time token.
     * For enabled users, validates the password against the stored hash.
     * For non-enabled users, validates the one-time token.
     *
     * @param authentication the authentication request object
     * @return authenticated token if credentials are valid
     * @throws AuthenticationException if authentication fails
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDetails userFromCache = customUserDetailService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userFromCache.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        } else if (!userFromCache.isEnabled()) {
            oneTimeTokenService.validateToken(password, username);
            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        } else
            throw new BadCredentialsException("External system authentication failed");
    }

    /**
     * Checks if this provider supports the given authentication type.
     *
     * @param authentication the class to check
     * @return true if the class is assignable from UsernamePasswordAuthenticationToken
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}