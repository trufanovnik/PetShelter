package com.ensep.petshelter.dto.user;

import lombok.Data;

@Data
public class UserEntityUpdateDTO {
    private String name;
    private String email;
    private String username;
}
