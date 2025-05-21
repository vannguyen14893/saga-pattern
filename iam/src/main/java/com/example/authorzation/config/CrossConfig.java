package com.example.authorzation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS) settings.
 * This class defines the CORS policies for the application, specifying which
 * origins, methods, and headers are allowed for cross-origin requests.
 */
@Configuration
public class CrossConfig {
    /**
     * Creates and configures the CORS configuration source.
     * This configuration:
     * - Allows requests from localhost:3000
     * - Permits GET, POST, PUT, PATCH, DELETE, and OPTIONS methods
     * - Allows specific headers: authorization, content-type, and x-auth-token
     * - Exposes x-auth-token header to clients
     * - Enables credentials in cross-origin requests
     *
     * @return configured CorsConfigurationSource for the application
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(List.of("x-auth-token"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
