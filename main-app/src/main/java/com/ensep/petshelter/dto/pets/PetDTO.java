package com.ensep.petshelter.dto.pets;

import com.ensep.petshelter.entities.AnimalKind;
import lombok.Data;

@Data
public class PetDTO {
    private Long id;
    private String name;
    private String description;
    private AnimalKind animalKind;
}
