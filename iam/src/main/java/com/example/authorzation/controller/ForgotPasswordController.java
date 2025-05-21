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

/**
 * Controller handling forgot password functionality including password reset requests
 * and password change operations.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
    private final DBMessageSourceConfig dbMessageSourceConfig;
    private final ForgotPasswordService forgotPasswordService;


    /**
     * Displays the forgot password page.
     *
     * @param model the Spring MVC model
     * @return the view name for forgot password page
     */
    @GetMapping
    public String forgotPasswordPage(Model model) {
        model.addAttribute("forgotPasswordRequest", new ForgotPasswordRequest());
        return "forgot-password";
    }

    /**
     * Processes the forgot password request and sends reset password email.
     *
     * @param model                 the Spring MVC model
     * @param forgotPasswordRequest the forgot password request data
     * @param bindingResult         validation results
     * @return the view name for either success or error page
     */
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

    /**
     * Displays the password reset page after validating the reset token.
     *
     * @param token the password reset token
     * @param model the Spring MVC model
     * @return the view name for password reset page
     */
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

    /**
     * Processes the password change request after reset.
     *
     * @param model                 the Spring MVC model
     * @param changePasswordRequest the password change request data
     * @param bindingResult         validation results
     * @return the view name for login page on success
     */
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
