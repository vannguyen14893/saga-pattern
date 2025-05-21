package com.saga.security.config;

import com.saga.security.dto.SecurityConfigProperties;
import com.saga.security.dto.SwaggerConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Configuration class for Spring Security in a resource server setup using OAuth2.
 * This interface provides default security configurations including:
 * - Web security customization for Swagger UI and API documentation endpoints
 * - CORS configuration based on security properties
 * - JWT-based OAuth2 resource server configuration
 * - Custom JWT decoder and authentication converter
 * <p>
 * The class uses Spring Security's new configuration style with component-based security
 * and method-level security enabled. It configures security filters, CORS policies,
 * and JWT token handling for OAuth2 resource server implementation.
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@EnableConfigurationProperties({SecurityConfigProperties.class, SwaggerConfigProperties.class})
public interface SecurityResourceConfig {

    @Bean
    default SecurityConfigProperties securityConfig() {
        return new SecurityConfigProperties();
    }


    @Bean
    default WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/resources/**",
                "/static/**");
    }

    @Bean
    default CorsConfigurationSource corsConfigurationSource(SecurityConfigProperties securityConfig) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedHeaders(securityConfig.getAllowedHeaders());
        cors.setAllowedMethods(securityConfig.getAllowedMethods());
        cors.setAllowCredentials(securityConfig.isAllowCredentials());
        cors.addAllowedOriginPattern(securityConfig.getAllowedOrigins());
        cors.setMaxAge(securityConfig.getMaxAge());
        source.registerCorsConfiguration(securityConfig.getAddMapping(), cors);
        return source;
    }


    @Bean
    @ConditionalOnBean(value = {CorsConfigurationSource.class, SecurityConfigProperties.class})
    default SecurityFilterChain securityFilter(HttpSecurity http, SecurityConfigProperties securityConfig) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(securityConfig.getPermitAll())
                        .permitAll().anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(new CustomBearerTokenAuthenticationEntryPoint())
                        .jwt(jwt -> jwt.decoder(jwtDecoder(securityConfig))
                                .jwtAuthenticationConverter(customJwtConverter())))
                .build();
    }

    @Bean
    default NimbusJwtDecoder jwtDecoder(SecurityConfigProperties securityConfig) {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(securityConfig.getIssuerUri());
        //jwtDecoder.setJwtValidator(new CustomAccessTokenValidator());
        return jwtDecoder;
    }

    private JwtAuthenticationConverter customJwtConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<String> scopes = jwt.getClaim("scope");
            return scopes.stream()
                    .map(scope -> new SimpleGrantedAuthority("SCOPE_" + scope))
                    .collect(Collectors.toList());
        });
        return converter;
    }
}
