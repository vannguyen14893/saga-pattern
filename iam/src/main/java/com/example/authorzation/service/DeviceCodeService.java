package com.example.authorzation.service;

import com.example.authorzation.dto.deviceCode.DeviceCodeResponse;
import com.example.authorzation.entity.DeviceCode;
import com.example.authorzation.repository.DeviceCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceCodeService {
    private final DeviceCodeRepository deviceCodeRepository;
    private final RegisteredClientService registeredClientService;

    public DeviceCodeResponse generateDeviceCode(String clientId) {
        RegisteredClient registeredClient = registeredClientService.findByClientId(clientId);
        return null;
    }

    public DeviceCode consumeDeviceCode(String deviceCode) {
        return null;
    }

    public DeviceCode findByUserCode(String deviceCode, String userCode) {
        return deviceCodeRepository.findByDeviceCodeAndUserCode(deviceCode, userCode);
    }

    public void approveDeviceCode(String deviceCode, Authentication userAuthentication) {

    }

    public void denyDeviceCode(String deviceCode) {

    }

}
