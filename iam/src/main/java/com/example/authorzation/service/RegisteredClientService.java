package com.example.authorzation.service;

import com.example.authorzation.exceptions.BusinessExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for managing registered OAuth2 clients.
 * Provides functionality to find and validate registered clients and their grant types.
 */
@Service
@RequiredArgsConstructor
public class RegisteredClientService {
    private final RegisteredClientRepository registeredClientRepository;

    /**
     * Finds a registered client by their client ID.
     *
     * @param clientId the ID of the client to search for
     * @return the RegisteredClient instance
     * @throws BusinessExceptionHandler if the client is not found
     */
    public RegisteredClient findByClientId(String clientId) {
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
        if (registeredClient == null) {
            throw new BusinessExceptionHandler("client_not_found");
        }
        return registeredClient;
    }

    /**
     * Validates and retrieves a specific grant type for a registered client.
     *
     * @param registeredClient the registered client to check
     * @param grantType        the grant type to validate
     * @return the validated grant type value
     * @throws BusinessExceptionHandler if the grant type is not found
     */
    public String getGrantType(RegisteredClient registeredClient, String grantType) {
        return registeredClient.getAuthorizationGrantTypes().stream().filter(item -> item.getValue().equals(grantType)).findFirst().orElseThrow(() -> new BusinessExceptionHandler("grant_types_not_found")).getValue();
    }
}
