package com.example.authorzation.config;

import com.example.authorzation.service.CustomUserDetailCache;
import com.example.authorzation.service.CustomUserDetailService;
import com.example.authorzation.service.OneTimeTokenService;
import com.saga.exceptions.config.DBMessageSourceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationEventPublisher authenticationEventPublisher;
    private final RememberMeConfig rememberMeConfig;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final AuthenticationCustomProvider authenticationCustomProvider;
    private final CustomUserDetailService customUserDetailService;
    private final AuthenticationCustomGrantTypePasswordProvider customGrantTypePasswordProvider;
    private final RegisteredClientRepository registeredClientRepository;
    private final OAuth2AuthorizationService oAuth2AuthorizationService;
    private final OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService;
    private final PasswordEncoder passwordEncoder;
    private final OneTimeTokenService oneTimeTokenService;
    private final CustomUserDetailCache customUserDetailCache;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http.cors(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling((exceptions) -> exceptions
                .defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint("/login"), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));
        http.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(withDefaults()));
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        // Get the default endpoint matcher for the token endpoint
        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .with(authorizationServerConfigurer, (authorizationServer) ->
                        authorizationServer.oidc(withDefaults())
                                .registeredClientRepository(registeredClientRepository)
                                .authorizationService(oAuth2AuthorizationService)
                                .authorizationServerSettings(authorizationServerSettings())
                                .authorizationConsentService(oAuth2AuthorizationConsentService))
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("custom/token/**").permitAll()
                                .anyRequest().authenticated());
//        authorizationServerConfigurer
//                .tokenEndpoint(tokenEndpoint ->
//                        tokenEndpoint
//                                .accessTokenRequestConverters(converters -> converters.add(new CustomPasswordAuthenticationConverter(registeredClientRepository, customUserDetailCache, passwordEncoder, oneTimeTokenService)))
//                                .authenticationProviders(providers -> providers.add(customGrantTypePasswordProvider)));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http.cors(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        //http.oneTimeTokenLogin(withDefaults());
        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers(
                                "/login*",
                                "/forgot-password/**",
                                "/hello-public",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/webjars/**",
                                "/static/**",
                                "/public/**",
                                "/favicon.ico",
                                "/custom/token/**",
                                "test/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("phone")
                        .failureHandler(customAuthenticationFailureHandler)
                        .defaultSuccessUrl("/hello", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .permitAll());
        http.rememberMe(rememberMe -> rememberMe.rememberMeServices(rememberMeConfig.rememberMeServices(customUserDetailService)));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession()
                .maximumSessions(-1)
                .maxSessionsPreventsLogin(true));
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/oauth2/token/**");
//    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:8088")
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        ProviderManager providerManager = new ProviderManager(authenticationCustomProvider);
        providerManager.setAuthenticationEventPublisher(authenticationEventPublisher);
        providerManager.setMessageSource(new DBMessageSourceConfig());
        return providerManager;
    }
}

