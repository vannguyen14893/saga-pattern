package com.example.authorzation.controller;

import com.example.authorzation.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling One-Time Password (OTP) related operations.
 * Provides endpoints for OTP generation, validation and management.
 */
@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpService otpService;

}
