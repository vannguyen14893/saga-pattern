package com.saga.exceptions.annotaions;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = EmailConstraintValidator.class)
public @interface ValidEmail {

    String message() default "â";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
