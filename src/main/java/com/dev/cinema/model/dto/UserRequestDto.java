package com.dev.cinema.model.dto;

import com.dev.cinema.security.validation.Email;
import com.dev.cinema.security.validation.ValidFieldRepeat;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@ValidFieldRepeat(
        password = "password",
        passwordRepeat = "passwordRepeat",
        message = "Passwords do not match!")
public class UserRequestDto {
    @Email
    private String email;
    @NonNull
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String passwordRepeat;
    @NonNull
    @Size(min = 3, max = 16)
    private String name;

    public UserRequestDto() {
    }
}
