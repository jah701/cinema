package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String login;
    private String password;
    private String name;
}
