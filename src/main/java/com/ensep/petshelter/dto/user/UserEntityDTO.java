package com.ensep.petshelter.dto.user;

import com.ensep.petshelter.dto.pet.PetDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserEntityDTO {
    private Long id;
    private String username;
    private List<PetDTO> likedPets;
}
