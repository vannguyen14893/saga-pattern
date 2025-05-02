package com.example.authorzation.controller;

import com.example.authorzation.service.OtpService;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController extends BaseController {
    private final OtpService otpService;

}
