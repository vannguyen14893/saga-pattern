package com.example.authorzation.service;

import com.example.authorzation.dto.LoginPasswordRequest;
import com.example.authorzation.dto.LoginSuccessResponse;
import com.example.authorzation.entity.User;
import com.example.authorzation.enums.MultifactorAuthenticationType;
import com.example.authorzation.exceptions.AuthenticationExceptionHandler;
import com.example.authorzation.exceptions.BusinessExceptionHandler;
import com.example.authorzation.repository.UserRepository;
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

    public LoginSuccessResponse login(LoginPasswordRequest loginPasswordRequest) {
        RegisteredClient registeredClient = registeredClientService.findByClientId(loginPasswordRequest.clientId());
        registeredClientService.getGrantType(registeredClient, loginPasswordRequest.grantType());
        try {
            User userDetails = (User) customUserDetailService.loadUserByUsername(loginPasswordRequest.username());
            boolean multifactorAuthenticationType = userDetails.getMultifactorAuthenticationType().equals(MultifactorAuthenticationType.SMS.name())
                    || userDetails.getMultifactorAuthenticationType().equals(MultifactorAuthenticationType.EMAIL.name());
            if (Strings.hasText(loginPasswordRequest.password())
                    && passwordEncoder.matches(loginPasswordRequest.password(), userDetails.getPassword())) {
                loginSuccess(loginPasswordRequest.username());
                String otp = multifactorAuthenticationType ? otpService.createOtp(userDetails) : googleAuthenticatorService.generateQRUrl(googleAuthenticatorService.generateKey(),userDetails.getUsername());
                return new LoginSuccessResponse(null, null, null, null, null, null, otp);
            } else if (!userDetails.isEnabled()) {
                oneTimeTokenService.validateToken(loginPasswordRequest.password(), loginPasswordRequest.username());
                OAuth2AccessTokenAuthenticationToken oAuth2AccessTokenAuthenticationToken = genTokenService.generateToken(registeredClient, new UsernamePasswordAuthenticationToken(userDetails, Collections.emptyList()), loginPasswordRequest.grantType(), loginPasswordRequest.username());
                return new LoginSuccessResponse(oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenValue(), oAuth2AccessTokenAuthenticationToken.getRefreshToken().getTokenValue(),
                        oAuth2AccessTokenAuthenticationToken.getRegisteredClient().getTokenSettings().getAccessTokenTimeToLive().getSeconds(),
                        oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenType().getValue(), oAuth2AccessTokenAuthenticationToken.getAdditionalParameters(), oAuth2AccessTokenAuthenticationToken.getAccessToken().getScopes(), null);
            } else if (Strings.hasText(loginPasswordRequest.otp())) {
                if (multifactorAuthenticationType) {
                    otpService.validateOtp(loginPasswordRequest.otp(), loginPasswordRequest.username());
                }
                OAuth2AccessTokenAuthenticationToken oAuth2AccessTokenAuthenticationToken = genTokenService.generateToken(registeredClient, new UsernamePasswordAuthenticationToken(userDetails, Collections.emptyList()), loginPasswordRequest.grantType(), loginPasswordRequest.username());
                return new LoginSuccessResponse(oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenValue(), oAuth2AccessTokenAuthenticationToken.getRefreshToken().getTokenValue(),
                        oAuth2AccessTokenAuthenticationToken.getRegisteredClient().getTokenSettings().getAccessTokenTimeToLive().getSeconds(),
                        oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenType().getValue(), oAuth2AccessTokenAuthenticationToken.getAdditionalParameters(), oAuth2AccessTokenAuthenticationToken.getAccessToken().getScopes(), null);
            }
            loginFail(loginPasswordRequest.username());
            throw new BusinessExceptionHandler("password.valid");
        } catch (AuthenticationExceptionHandler e) {
            throw new BusinessExceptionHandler(e.getMessage());
        }
    }

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

    public void loginSuccess(String username) {
        User user = userRepository.findByPhone(username);
        user.setCountLoginAttempt(0);
        user.setLastFailedLoginDate(LocalDateTime.now());
        user.setAccountNonLocked(true);
        userRepository.save(user);
        customUserDetailCache.putUserInCache(user);
    }
}
