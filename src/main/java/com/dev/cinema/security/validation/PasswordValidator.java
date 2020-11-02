package com.dev.cinema.security.validation;

import com.dev.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidFieldRepeat, UserRequestDto> {
    private String password;
    private String passwordRepeat;

    @Override
    public void initialize(ValidFieldRepeat constraint) {
        this.password = constraint.password();
        this.passwordRepeat = constraint.passwordRepeat();
    }

    @Override
    public boolean isValid(UserRequestDto dto, ConstraintValidatorContext context) {
        return dto.getPassword() != null && dto.getPassword().equals(dto.getPasswordRepeat());
    }
}
