package com.example.authorzation.controller;

import com.example.authorzation.dto.ChangePasswordRequest;
import com.example.authorzation.dto.ForgotPasswordRequest;
import com.example.authorzation.entity.PasswordResetToken;
import com.example.authorzation.entity.User;
import com.example.authorzation.service.ForgotPasswordService;
import com.saga.exceptions.config.DBMessageSourceConfig;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
    private final DBMessageSourceConfig dbMessageSourceConfig;
    private final ForgotPasswordService forgotPasswordService;


    @GetMapping
    public String forgotPasswordPage(Model model) {
        model.addAttribute("forgotPasswordRequest", new ForgotPasswordRequest());
        return "forgot-password";
    }

    @PostMapping
    public String sendMailForgotPassword(Model model, @Valid @ModelAttribute("forgotPasswordRequest") ForgotPasswordRequest forgotPasswordRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            return "forgot-password";
        }
        PasswordResetToken passwordResetToken = forgotPasswordService.create(forgotPasswordRequest.getEmail());
        if (passwordResetToken != null) {
            model.addAttribute("message_success", dbMessageSourceConfig.getMessages("forgot_password_success"));
            return "login";
        }
        model.addAttribute("message", dbMessageSourceConfig.getMessages("user_not_found"));
        return "forgot-password";
    }

    @GetMapping("/save")
    public String resetPassword(@RequestParam String token, Model model) {
        String valid = forgotPasswordService.validatePasswordResetToken(token);
        if (valid != null) {
            model.addAttribute("message", dbMessageSourceConfig.getMessages(valid));
            model.addAttribute("disable", true);
        }
        if (token != null) {
            model.addAttribute("token", token);
        }
        model.addAttribute("changePasswordRequest", new ChangePasswordRequest());
        return "save-password";
    }

    @PostMapping("/save")
    public String savePassword(Model model, @Valid @ModelAttribute("changePasswordRequest") ChangePasswordRequest changePasswordRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            model.addAttribute("token", changePasswordRequest.getToken());
            return "save-password";
        }
        User user = forgotPasswordService.save(changePasswordRequest);
        if (user != null) {
            model.addAttribute("message_success", dbMessageSourceConfig.getMessages("save_password_success"));
        }
        return "login";
    }
}
