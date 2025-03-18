package com.saga.exceptions.annotaions.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<ValidPhone, String> {

    @Override
    public void initialize(ValidPhone arg0) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && phone.matches("^((\\\\+91-?)|0)?[0-9]{10}$");
    }
}
