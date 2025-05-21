package com.saga.exceptions.annotaions.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Custom constraint validator for phone number validation.
 * Implements the validation logic for the {@link ValidPhone} annotation.
 */
public class PhoneConstraintValidator implements ConstraintValidator<ValidPhone, String> {

    @Override
    public void initialize(ValidPhone arg0) {
    }

    /**
     * Validates if the given phone number matches the required format.
     * The phone number must be a 10-digit number, optionally prefixed with +91- or 0.
     *
     * @param phone   the phone number to validate
     * @param context the constraint validator context
     * @return true if the phone number is valid, false otherwise
     */
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && phone.matches("^((\\\\+91-?)|0)?[0-9]{10}$");
    }
}
