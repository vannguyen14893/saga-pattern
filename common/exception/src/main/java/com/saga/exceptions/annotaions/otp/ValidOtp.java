package com.saga.exceptions.annotaions.otp;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = OtpConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidOtp {

    String message() default "otp_invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String password();

    String otp();
}
