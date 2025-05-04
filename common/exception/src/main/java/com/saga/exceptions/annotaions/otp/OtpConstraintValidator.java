package com.saga.exceptions.annotaions.otp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
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
            Object passwordField = getFieldValue(value, password);
            Object otpField = getFieldValue(value, otp);
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

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
