package com.dev.cinema.security.validation;

import com.dev.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<ValidFieldRepeat, UserRequestDto> {
    private String password;
    private String passwordRepeat;

    @Override
    public void initialize(ValidFieldRepeat constraint) {
        this.password = constraint.password();
        this.passwordRepeat = constraint.passwordRepeat();
    }

    @Override
    public boolean isValid(UserRequestDto value, ConstraintValidatorContext context) {
        String fieldValue = (String) new BeanWrapperImpl(value)
                .getPropertyValue(password);
        String fieldMatchValue = (String) new BeanWrapperImpl(value)
                .getPropertyValue(passwordRepeat);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
