package com.ensep.petshelter.dto.shelter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShelterRegistrationRequest {

    @NotBlank(message = "Дайте название приюту")
    private String title;

    @NotBlank(message = "Укажите город приюта")
    private String city;

    @NotBlank(message = "Укажите контактный email")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Придумайте пароль")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;

    private String phoneNumber;
}
