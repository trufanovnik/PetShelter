package com.ensep.petshelter.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
