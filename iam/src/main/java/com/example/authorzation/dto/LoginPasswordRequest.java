package com.example.authorzation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.saga.exceptions.annotaions.otp.ValidOtp;
import com.saga.exceptions.annotaions.password.ValidPassword;
import com.saga.exceptions.annotaions.phone.ValidPhone;
import jakarta.validation.constraints.NotBlank;

//@ValidOtp(password = "password", otp = "otp")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LoginPasswordRequest(

        @ValidPhone(message = "phone.valid")
        String username,
        @ValidPassword(message = "password.valid")
        String password,
        @NotBlank(message = "clientId.not.blank")
        String clientId,
        @NotBlank(message = "clientSecret.not.blank")
        String clientSecret,
        @NotBlank(message = "grantType.not.blank")
        String grantType,
        String otp
) {
}
