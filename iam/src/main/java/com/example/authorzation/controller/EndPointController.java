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

/**
 * REST controller endpoint for handling custom token generation requests.
 * Provides endpoint for user authentication and token generation using login credentials.
 */
@RestController
@RequestMapping("/custom/token")
@RequiredArgsConstructor
public class EndPointController extends BaseController {
    private final LoginService loginService;

    /**
     * Handles token generation requests by authenticating user credentials.
     *
     * @param loginPasswordRequest The request containing login credentials
     * @return ResponseEntity containing the generated token response
     */
    @PostMapping
    public ResponseEntity<?> token(@RequestBody @Valid LoginPasswordRequest loginPasswordRequest) {
        return execute(loginService.login(loginPasswordRequest), "200");
    }
}
