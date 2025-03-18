package com.example.authorzation.config;

import com.example.authorzation.service.CustomUserDetailCache;
import com.example.authorzation.service.CustomUserDetailsChecker;
import com.saga.exceptions.config.DBMessageSourceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final AuthenticationEventPublisher authenticationEventPublisher;
    private final CustomUserDetailCache customUserDetailCache;
    private final CustomUserDetailsChecker customUserDetailsChecker;
    private final PasswordEncoder passwordEncoder;
    private final RememberMeConfig rememberMeConfig;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http.cors(Customizer.withDefaults());
        http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint("/login"), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
                .oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .with(authorizationServerConfigurer, (authorizationServer) ->
                        authorizationServer.oidc(Customizer.withDefaults()))
                .authorizeHttpRequests((authorize) ->
                        authorize.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http.cors(Customizer.withDefaults());
        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("login*","/logout","/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login")
                        .usernameParameter("phone")
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll())
                .rememberMe((remember) -> remember.rememberMeServices(rememberMeConfig.rememberMeServices(userDetailsService)));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession()
                .maximumSessions(2)
                .maxSessionsPreventsLogin(true));
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**","/css/**", "/js/**");
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:8088")
                .build();
    }

    @Bean
    @Lazy
    public ProviderManager authManagerBean() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setUserCache(customUserDetailCache);
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setHideUserNotFoundExceptions(true);
        authProvider.setPreAuthenticationChecks(customUserDetailsChecker);
        authProvider.setMessageSource(new DBMessageSourceConfig());
        authProvider.setHideUserNotFoundExceptions(true);
        ProviderManager providerManager = new ProviderManager(authProvider);
        providerManager.setAuthenticationEventPublisher(authenticationEventPublisher);
        return providerManager;
    }

}
