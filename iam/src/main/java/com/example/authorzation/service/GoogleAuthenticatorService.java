package com.example.authorzation.service;

import com.example.authorzation.entity.User;
import com.example.authorzation.exceptions.BusinessExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
@Slf4j
public class GoogleAuthenticatorService {
    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    public String generateQRUrl(User user) {
        try {
            return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", "IAM", user.getPhone(), user.getSecret(), "IAM"), "UTF-8");
        } catch (Exception e) {
            throw new BusinessExceptionHandler("Error generate QR url: " + e.getMessage());
        }

    }
}
