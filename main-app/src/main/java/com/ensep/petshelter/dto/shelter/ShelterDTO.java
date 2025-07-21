package com.ensep.petshelter.dto.shelter;

import com.ensep.petshelter.dto.pets.PetDTO;
import lombok.Data;

import java.util.List;

@Data
public class ShelterDTO {
    private Long id;
    private String title;
    private String city;
    private String number;
    private String email;
    private List<PetDTO> pets;
}
