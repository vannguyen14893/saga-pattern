package com.saga.exceptions.annotaions.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Custom validator that implements validation logic for matching password fields.
 * This validator ensures that two specified fields in an object contain matching values,
 * typically used for password confirmation validation.
 */
public class PasswordValueMatchValidator implements ConstraintValidator<PasswordValueMatch, Object> {
    /**
     * Name of the first field to compare
     */
    private String firstFieldName;
    /**
     * Name of the second field to compare
     */
    private String secondFieldName;
    /**
     * Message to be used in case of validation failure
     */
    private String message;

    /**
     * Initializes the validator with values from the annotation.
     *
     * @param constraintAnnotation the annotation instance containing the validation rules
     */
    @Override
    public void initialize(final PasswordValueMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    /**
     * Validates that two specified fields have matching values.
     *
     * @param value                      the object containing the fields to validate
     * @param constraintValidatorContext context in which the constraint is evaluated
     * @return true if the fields match or are both null, false otherwise
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {}
        if (!valid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
