package com.dev.cinema.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String emailRegex = "^[\\w!#$%&'*+\\=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&'*+\\=?`{|}~^-]+)"
                + "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return value != null && value.matches(emailRegex);
    }
}
