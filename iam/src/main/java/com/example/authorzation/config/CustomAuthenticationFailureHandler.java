package com.example.authorzation.config;

import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import com.example.authorzation.exceptions.UserDetailNotFound;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom handler for authentication failures in the application.
 * This handler extends Spring Security's SimpleUrlAuthenticationFailureHandler to provide
 * specific redirect behaviors based on different types of authentication failures.
 * It handles various scenarios such as:
 * - Maximum session exceeded
 * - Custom authentication exceptions
 * - Bad credentials
 * - User details not found
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * Handles authentication failures by redirecting to appropriate URLs with specific error messages.
     *
     * @param request   The HTTP request
     * @param response  The HTTP response
     * @param exception The authentication exception that occurred during the authentication process
     * @throws IOException      If an I/O error occurs during the redirect
     * @throws ServletException If a servlet error occurs
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception.getClass().isAssignableFrom(SessionAuthenticationException.class)) {
            response.sendRedirect(request.getContextPath() + "/login?message=max_session");
            return;
        }
        if (exception.getClass().isAssignableFrom(AuthenticationExceptionHandler.class)) {
            response.sendRedirect(request.getContextPath() + "/login?message=" + exception.getMessage());
            return;
        }
        if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            response.sendRedirect(request.getContextPath() + "/login?message=password.valid");
            return;
        }
        if (exception.getClass().isAssignableFrom(UserDetailNotFound.class)) {
            response.sendRedirect(request.getContextPath() + "/login?message=" + exception.getMessage());
        }

    }
}
