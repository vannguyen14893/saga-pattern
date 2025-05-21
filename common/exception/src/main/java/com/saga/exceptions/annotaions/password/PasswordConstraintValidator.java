package com.saga.exceptions.annotaions.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Custom password constraint validator that implements password validation rules using Passay library.
 * Current validation rules include:
 * - Password length must be between 8 and 256 characters
 * - Must contain at least 1 digit
 * - Must not contain whitespace characters
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    /**
     * Initializes the validator in preparation for {@link #isValid(String, ConstraintValidatorContext)} calls.
     * No specific initialization is required for this validator.
     *
     * @param arg0 the {@link ValidPassword} annotation to initialize with
     */
    @Override
    public void initialize(ValidPassword arg0) {
    }

    /**
     * Validates the password against defined rules using Passay validator.
     *
     * @param password the password string to validate
     * @param context  context in which the constraint is evaluated
     * @return true if the password meets all validation rules or is empty/null, false otherwise
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (StringUtils.hasText(password)) {
            PasswordValidator validator = new PasswordValidator(Arrays.asList(
                    // length between 8 and 100 characters
                    new LengthRule(8, 256),
                    new CharacterRule(EnglishCharacterData.Digit, 1),
                    // no whitespace
                    new WhitespaceRule()

            ));
            RuleResult result = validator.validate(new PasswordData(password));
            return result.isValid();
        }
        return true;
    }
}
