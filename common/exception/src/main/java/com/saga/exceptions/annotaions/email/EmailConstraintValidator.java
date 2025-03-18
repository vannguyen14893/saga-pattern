package com.saga.exceptions.annotaions.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail arg0) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && EmailValidator.getInstance().isValid(email);
    }
}
