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

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

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
