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

@RestController
@RequestMapping("/oauth2/device_authorization")
@RequiredArgsConstructor
public class DeviceAuthorizationEndpoint extends BaseController {
    private final DeviceCodeService deviceCodeService;

    @PostMapping
    public ResponseEntity<ResponseSuccess<DeviceCodeResponse>> deviceAuthorization(
            @RequestParam("client_id") String clientId) {
        return execute(deviceCodeService.generateDeviceCode(clientId), 200);
    }
}
