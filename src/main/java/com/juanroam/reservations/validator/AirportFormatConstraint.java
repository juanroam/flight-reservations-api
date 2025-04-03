package com.juanroam.reservations.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AirportFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AirportFormatConstraint {

    String message() default "Invalid format for the airport";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
