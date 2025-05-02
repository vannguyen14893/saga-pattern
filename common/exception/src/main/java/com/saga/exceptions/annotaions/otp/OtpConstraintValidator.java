package com.saga.exceptions.annotaions.otp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class OtpConstraintValidator implements ConstraintValidator<ValidOtp, Object> {
    private String password;
    private String otp;
    private String message;

    @Override
    public void initialize(ValidOtp arg0) {
        this.password = arg0.password();
        this.otp = arg0.otp();
        message = arg0.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object passwordField = BeanUtils.getProperty(value, password);
            Object otpField = BeanUtils.getProperty(value, otp);
            if (!StringUtils.hasText(Objects.toString(passwordField)) && otpField != null) {
                boolean check = otpField.toString().length() == 6;
                if (!check) {
                    context.buildConstraintViolationWithTemplate(message)
                            .addPropertyNode(otp)
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    return false;
                }
                return true;
            }
            if (!StringUtils.hasText(Objects.toString(passwordField))) {
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(otp)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
