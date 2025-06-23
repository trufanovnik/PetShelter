package com.ensep.petshelter.dto;

import com.ensep.petshelter.entities.AnimalKind;
import lombok.Data;

@Data
public class PetDto {
    private Long id;
    private String name;
    private String description;
    private AnimalKind animalKind;
}
