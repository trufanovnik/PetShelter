package com.ensep.petshelter.dto.user;

import com.ensep.petshelter.dto.pets.PetDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserLikedPetsDto {
    private Long id;
    private String username;
    private List<PetDTO> likedPets;
}
