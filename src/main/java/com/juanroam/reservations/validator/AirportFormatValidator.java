package com.juanroam.reservations.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AirportFormatValidator
        implements ConstraintValidator<AirportFormatConstraint, String> {

    @Override
    public void initialize(AirportFormatConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return field != null &&
                field.length() == 3 &&
                field.matches("[A-Z]+");
    }
}
