package com.example.authorzation.controller;

import com.example.authorzation.service.OneTimeTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final OneTimeTokenService oneTimeTokenService;

    @GetMapping
    public String test() {
        return oneTimeTokenService.generateToken("1234567890");
    }
}
