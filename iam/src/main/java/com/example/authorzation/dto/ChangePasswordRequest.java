package com.example.authorzation.dto;

import com.saga.exceptions.annotaions.password.PasswordValueMatch;
import com.saga.exceptions.annotaions.password.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                first = "passwordNew",
                second = "passwordConfirm",
                message = "{password_no_match}"
        )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    private String token;
    @ValidPassword(message = "{password.valid}")
    private String passwordNew;
    @ValidPassword(message = "{password.valid}")
    private String passwordConfirm;
}
