package com.example.authorzation.dto;

import com.saga.exceptions.annotaions.email.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordRequest {
    @ValidEmail(message = "{email.valid}")
    private String email;
}
