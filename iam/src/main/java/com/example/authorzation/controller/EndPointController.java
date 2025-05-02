package com.example.authorzation.controller;

import com.example.authorzation.dto.LoginPasswordRequest;
import com.example.authorzation.service.LoginService;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom/token")
@RequiredArgsConstructor
public class EndPointController extends BaseController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> token(@RequestBody @Valid LoginPasswordRequest loginPasswordRequest) {
        return execute(loginService.login(loginPasswordRequest), 200);
    }
}
