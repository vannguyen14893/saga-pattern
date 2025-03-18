package com.saga.security.config;

import com.saga.security.dto.SecurityConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@EnableConfigurationProperties({SecurityConfigProperties.class})
public interface SecurityResourceConfig {
    @Bean
    default SecurityConfigProperties securityConfig() {
        return new SecurityConfigProperties();
    }
    @Bean
    default WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**");
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
                .authorizeHttpRequests(auth -> auth.requestMatchers(securityConfig.getPermitAll())
                        .permitAll().anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(securityConfig.getIssuerUri()))))
                .build();
    }

}
