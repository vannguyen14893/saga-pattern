package com.saga.exceptions.annotaions.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password != null) {
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
