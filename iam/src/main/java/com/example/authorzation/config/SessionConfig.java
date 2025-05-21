package com.example.authorzation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Configuration class for session management setup.
 * Provides beans for HTTP session event handling and session registry.
 */
@Configuration
public class SessionConfig {
    /**
     * Creates a HttpSessionEventPublisher bean for session event handling.
     * Publishes session creation and destruction events to the Spring application context.
     *
     * @return HttpSessionEventPublisher instance for session event management
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * Creates a SessionRegistry bean for tracking session information.
     * Maintains a registry of active user sessions and their details.
     *
     * @return SessionRegistry instance for session tracking
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
