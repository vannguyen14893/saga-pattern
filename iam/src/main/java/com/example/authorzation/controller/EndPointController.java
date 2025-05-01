package com.example.authorzation.controller;

import com.example.authorzation.dto.LoginSuccessResponse;
import com.example.authorzation.service.CustomUserDetailService;
import com.example.authorzation.service.GenTokenService;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/custom/token")
@RequiredArgsConstructor
public class EndPointController extends BaseController {
    private final RegisteredClientRepository registeredClientRepository;
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GenTokenService genTokenService;


    @PostMapping
    public ResponseEntity<?> token(
            @RequestParam Map<String, String> parameters,
            @RequestHeader HttpHeaders headers) {
        String clientId = parameters.get(OAuth2ParameterNames.CLIENT_ID);
        if (!StringUtils.hasText(clientId)) {
            return execute(400, "Client id không được để trống");
        }
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
        if (registeredClient == null) {
            return execute(400, "Client id không được hỗ trợ");
        }
        String grantType = parameters.get(OAuth2ParameterNames.GRANT_TYPE);
        if (!StringUtils.hasText(grantType)) {
            return execute(400, "Grant type không được để trống");
        }
        if (registeredClient.getAuthorizationGrantTypes().stream().filter(item -> item.getValue().equals(grantType)).isParallel()) {
            return execute(400, "Grant type không được hỗ trợ");
        }
        String username = parameters.get(OAuth2ParameterNames.USERNAME);
        String password = parameters.get(OAuth2ParameterNames.PASSWORD);
        // Validate required parameters
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return execute(400, "username hoặc password không được để trống");
        }
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
            authenticationManager.authenticate(authentication);
            OAuth2AccessTokenAuthenticationToken oAuth2AccessTokenAuthenticationToken = genTokenService.generateToken(registeredClient, authentication, grantType, username);
            LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse(oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenValue(), oAuth2AccessTokenAuthenticationToken.getRefreshToken().getTokenValue(),
                    oAuth2AccessTokenAuthenticationToken.getAccessToken().getIssuedAt().getEpochSecond(), oAuth2AccessTokenAuthenticationToken.getAccessToken().getExpiresAt().getEpochSecond(),
                    oAuth2AccessTokenAuthenticationToken.getAccessToken().getTokenType().getValue(), oAuth2AccessTokenAuthenticationToken.getAdditionalParameters(), oAuth2AccessTokenAuthenticationToken.getAccessToken().getScopes());
            return execute(loginSuccessResponse, 200);
        }
        return null;
    }
}
