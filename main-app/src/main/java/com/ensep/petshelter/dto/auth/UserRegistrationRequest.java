package com.ensep.petshelter.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "Укажите username")
    private String username;

    @NotBlank(message = "Придумайте пароль")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;
}
