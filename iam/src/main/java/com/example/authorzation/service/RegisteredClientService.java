package com.example.authorzation.service;

import com.example.authorzation.exceptions.BusinessExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisteredClientService {
    private final RegisteredClientRepository registeredClientRepository;

    public RegisteredClient findByClientId(String clientId) {
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
        if (registeredClient == null) {
            throw new BusinessExceptionHandler("client_not_found");
        }
        return registeredClient;
    }

    public String getGrantType(RegisteredClient registeredClient, String grantType) {
        return registeredClient.getAuthorizationGrantTypes().stream().filter(item -> item.getValue().equals(grantType)).findFirst().orElseThrow(() -> new BusinessExceptionHandler("grant_types_not_found")).getValue();
    }
}
