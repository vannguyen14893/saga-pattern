package com.example.authorzation.config;

import com.example.authorzation.service.OneTimeTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Handler for successful one-time token generation during login.
 * Implements redirect functionality after token generation by delegating to a RedirectOneTimeTokenGenerationSuccessHandler.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OttLoginLinkSuccessHandler implements OneTimeTokenGenerationSuccessHandler {
    private OneTimeTokenService oneTimeTokenService;
    /**
     * Handler that performs the actual redirect after successful token generation.
     * Configured to redirect to "/hello-public" endpoint.
     */
    private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler("/hello-public");

    /**
     * Handles the successful generation of a one-time token by delegating to the redirect handler.
     *
     * @param request      The HTTP request
     * @param response     The HTTP response
     * @param oneTimeToken The generated one-time token
     * @throws IOException      If an I/O error occurs during redirect
     * @throws ServletException If a servlet error occurs during redirect
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws IOException, ServletException {
        redirectHandler.handle(request, response, oneTimeToken);
    }
}
