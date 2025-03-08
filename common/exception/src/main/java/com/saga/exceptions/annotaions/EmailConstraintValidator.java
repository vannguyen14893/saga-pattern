package com.saga.exceptions.annotaions;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail arg0) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null;
    }
}
