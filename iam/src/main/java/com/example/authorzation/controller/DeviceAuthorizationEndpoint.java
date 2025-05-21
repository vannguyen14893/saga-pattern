package com.example.authorzation.controller;

import com.example.authorzation.dto.deviceCode.DeviceCodeResponse;
import com.example.authorzation.service.DeviceCodeService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller endpoint for handling device authorization requests in OAuth 2.0 flow.
 * Provides endpoints for generating device codes and managing device authorization process.
 */
@RestController
@RequestMapping("/oauth2/device_authorization")
@RequiredArgsConstructor
public class DeviceAuthorizationEndpoint extends BaseController {
    private final DeviceCodeService deviceCodeService;

    /**
     * Handles device authorization requests by generating a device code for the given client.
     *
     * @param clientId The ID of the client requesting device authorization
     * @return ResponseEntity containing the generated device code response
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<DeviceCodeResponse>> deviceAuthorization(
            @RequestParam("client_id") String clientId) {
        return execute(deviceCodeService.generateDeviceCode(clientId), "200");
    }
}
