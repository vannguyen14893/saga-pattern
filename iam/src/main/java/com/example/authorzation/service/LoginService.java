package com.example.authorzation.service;

import com.example.authorzation.dto.CustomTokenType;
import com.example.authorzation.dto.LoginPasswordRequest;
import com.example.authorzation.dto.LoginSuccessResponse;
import com.example.authorzation.entity.User;
import com.example.authorzation.enums.MultifactorAuthenticationType;
import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import com.example.authorzation.exceptions.BusinessExceptionHandler;
import com.example.authorzation.repository.UserRepository;
import com.saga.dto.enums.CustomGrantType;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * Service class handling user authentication and login-related operations.
 * Manages password-based authentication, multi-factor authentication, and token generation.
 * Handles login attempts tracking and account locking functionality.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final CustomUserDetailCache customUserDetailCache;
    private final PasswordEncoder passwordEncoder;
    private final GenTokenService genTokenService;
    private final OneTimeTokenService oneTimeTokenService;
    private final CustomUserDetailService customUserDetailService;
    private final RegisteredClientService registeredClientService;
    private final OtpService otpService;
    private final GoogleAuthenticatorService googleAuthenticatorService;

    /**
     * Authenticates a user based on the provided login credentials and generates appropriate tokens.
     * Supports multiple authentication methods including password, SMS, email, and Google Authenticator.
     * Tracks failed login attempts and manages account locking.
     *
     * @param loginPasswordRequest the login request containing credentials and authentication parameters
     * @return LoginSuccessResponse containing generated tokens and authentication details
     * @throws BusinessExceptionHandler       if authentication fails or credentials are invalid
     * @throws AuthenticationExceptionHandler if authentication process encounters an error
     */
    public LoginSuccessResponse login(LoginPasswordRequest loginPasswordRequest) {
        RegisteredClient registeredClient = registeredClientService.findByClientId(loginPasswordRequest.clientId());
        String grantType = loginPasswordRequest.grantType();
        registeredClientService.getGrantType(registeredClient, grantType);
        try {
            User userDetails = (User) customUserDetailService.loadUserByUsername(loginPasswordRequest.username());
            boolean multifactorAuthenticationType = userDetails.getMultifactorAuthenticationType().equals(MultifactorAuthenticationType.SMS.name())
                    || userDetails.getMultifactorAuthenticationType().equals(MultifactorAuthenticationType.EMAIL.name());
            OAuth2AccessTokenAuthenticationToken oAuth2AccessTokenAuthenticationToken;
            if (grantType.equals(CustomGrantType.CUSTOM_PASSWORD_GRANT.getValue()) && Strings.hasText(loginPasswordRequest.password())
                    && passwordEncoder.matches(loginPasswordRequest.password(), userDetails.getPassword())) {
                loginSuccess(loginPasswordRequest.username());
                String otp = multifactorAuthenticationType ? otpService.createOtp(userDetails) : googleAuthenticatorService.generateQRUrl(userDetails);
                oAuth2AccessTokenAuthenticationToken = genTokenService.generateToken(registeredClient, new UsernamePasswordAuthenticationToken(userDetails, Collections.emptyList()),
                        loginPasswordRequest.grantType(), loginPasswordRequest.username(), CustomTokenType.CUSTOM_PASSWORD_GRANT);
                return new LoginSuccessResponse(oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenValue(), oAuth2AccessTokenAuthenticationToken.getRefreshToken().getTokenValue(),
                        oAuth2AccessTokenAuthenticationToken.getRegisteredClient().getTokenSettings().getAccessTokenTimeToLive().getSeconds(),
                        CustomGrantType.CUSTOM_PASSWORD_GRANT.getValue(), oAuth2AccessTokenAuthenticationToken.getAdditionalParameters(), oAuth2AccessTokenAuthenticationToken.getAccessToken().getScopes(),
                        otp, userDetails.getMultifactorAuthenticationType());
            } else if (grantType.equals(CustomGrantType.CUSTOM_REGIS_GRANT.getValue()) && !userDetails.isEnabled()) {
                oneTimeTokenService.validateToken(loginPasswordRequest.password(), loginPasswordRequest.username());
                oAuth2AccessTokenAuthenticationToken = genTokenService.generateToken(registeredClient, new UsernamePasswordAuthenticationToken(userDetails, Collections.emptyList()), loginPasswordRequest.grantType(),
                        loginPasswordRequest.username(), CustomTokenType.CUSTOM_REGIS_GRANT);
                return new LoginSuccessResponse(oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenValue(), oAuth2AccessTokenAuthenticationToken.getRefreshToken().getTokenValue(),
                        oAuth2AccessTokenAuthenticationToken.getRegisteredClient().getTokenSettings().getAccessTokenTimeToLive().getSeconds(),
                        CustomGrantType.CUSTOM_REGIS_GRANT.getValue(), oAuth2AccessTokenAuthenticationToken.getAdditionalParameters(), oAuth2AccessTokenAuthenticationToken.getAccessToken().getScopes(), null, userDetails.getMultifactorAuthenticationType());
            } else if ((grantType.equals(CustomGrantType.CUSTOM_SMS_GRANT.getValue()) ||
                    grantType.equals(CustomGrantType.CUSTOM_GOOGLE_AUTH_GRANT.getValue()) ||
                    grantType.equals(CustomGrantType.CUSTOM_EMAIL_GRANT.getValue())) &&
                    Strings.hasText(loginPasswordRequest.otp())) {
                Integer code = multifactorAuthenticationType ? otpService.validateOtp(loginPasswordRequest.otp(), loginPasswordRequest.username())
                        : googleAuthenticatorService.isValid(userDetails.getSecret(), loginPasswordRequest.otp());
                oAuth2AccessTokenAuthenticationToken = genTokenService.generateToken(registeredClient, new UsernamePasswordAuthenticationToken(userDetails, Collections.emptyList()),
                        loginPasswordRequest.grantType(), loginPasswordRequest.username(), multifactorAuthenticationType ? CustomTokenType.CUSTOM_SMS_GRANT : CustomTokenType.CUSTOM_GOOGLE_AUTH_GRANT);
                return new LoginSuccessResponse(oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenValue(), oAuth2AccessTokenAuthenticationToken.getRefreshToken().getTokenValue(),
                        oAuth2AccessTokenAuthenticationToken.getRegisteredClient().getTokenSettings().getAccessTokenTimeToLive().getSeconds(),
                        oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenType().getValue(), oAuth2AccessTokenAuthenticationToken.getAdditionalParameters(), oAuth2AccessTokenAuthenticationToken.getAccessToken().getScopes(), null, userDetails.getMultifactorAuthenticationType());
            }
            loginFail(loginPasswordRequest.username());
            throw new BusinessExceptionHandler("password.valid");
        } catch (AuthenticationExceptionHandler e) {
            throw new BusinessExceptionHandler(e.getMessage());
        }
    }

    /**
     * Records a failed login attempt for the specified user.
     * Increments the failed login counter and locks the account after three failed attempts.
     *
     * @param username the username/phone number of the user who failed to log in
     */
    public void loginFail(String username) {
        User user = userRepository.findByPhone(username);
        int countLoginAttempt = user.getCountLoginAttempt() + 1;
        user.setCountLoginAttempt(countLoginAttempt);
        user.setLastFailedLoginDate(LocalDateTime.now());
        if (user.getCountLoginAttempt() > 2) {
            user.setAccountNonLocked(false);
        }
        User save = userRepository.save(user);
        customUserDetailCache.putUserInCache(save);
    }

    /**
     * Records a successful login attempt for the specified user.
     * Resets the failed login counter and unlocks the account.
     *
     * @param username the username/phone number of the user who successfully logged in
     */
    public void loginSuccess(String username) {
        User user = userRepository.findByPhone(username);
        user.setCountLoginAttempt(0);
        user.setLastFailedLoginDate(LocalDateTime.now());
        user.setAccountNonLocked(true);
        userRepository.save(user);
        customUserDetailCache.putUserInCache(user);
    }
}
