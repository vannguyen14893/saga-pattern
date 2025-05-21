package com.saga.exceptions.annotaions.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Validator implementation for email address validation.
 * Implements ConstraintValidator interface to validate email addresses
 * using Apache Commons EmailValidator.
 */
public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

    /**
     * Initializes the validator. No specific initialization needed for email validation.
     *
     * @param arg0 the annotation instance for this validator
     */
    @Override
    public void initialize(ValidEmail arg0) {
    }

    /**
     * Validates if the given string is a valid email address.
     * Uses Apache Commons EmailValidator for the actual validation.
     *
     * @param email   the email address to validate
     * @param context the constraint validator context
     * @return true if email is valid, false otherwise
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && EmailValidator.getInstance().isValid(email);
    }
}
