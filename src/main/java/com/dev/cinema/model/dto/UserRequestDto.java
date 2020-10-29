package com.dev.cinema.model.dto;

import com.dev.cinema.security.validation.Email;
import com.dev.cinema.security.validation.ValidFieldRepeat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@ValidFieldRepeat(
        password = "password",
        passwordRepeat = "passwordRepeat",
        message = "Passwords do not match!")
public class UserRequestDto {
    @Email
    private String email;
    @NotNull(message = "Password can not be null")
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    @NotNull(message = "Password can not be null")
    private String passwordRepeat;
    @NotNull(message = "Name can not be null")
    @Size(min = 3, max = 16)
    private String name;

    public UserRequestDto() {
    }
}
