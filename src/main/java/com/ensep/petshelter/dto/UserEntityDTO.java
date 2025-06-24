package com.ensep.petshelter.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserEntityDTO {
    private Long id;
    private String username;
    private List<PetDTO> likedPets;
}
