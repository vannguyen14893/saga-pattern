package com.saga.exceptions.annotaions.otp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Validator class that implements validation logic for OTP (One-Time Password) constraints.
 * This validator ensures that OTP field contains valid data when password is not provided.
 */
public class OtpConstraintValidator implements ConstraintValidator<ValidOtp, Object> {
    private String password;
    private String otp;
    private String message;

    /**
     * Initializes the validator with constraint annotation data.
     *
     * @param arg0 the annotation instance containing validation rules
     */
    @Override
    public void initialize(ValidOtp arg0) {
        this.password = arg0.password();
        this.otp = arg0.otp();
        message = arg0.message();
    }

    /**
     * Validates that when password is not provided, OTP must be present and exactly 6 characters long.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return true if validation passes, false otherwise
     */
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

    /**
     * Retrieves field value from an object using reflection.
     *
     * @param object    the object to get field value from
     * @param fieldName name of the field to retrieve
     * @return value of the specified field
     * @throws Exception if field access fails
     */
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
