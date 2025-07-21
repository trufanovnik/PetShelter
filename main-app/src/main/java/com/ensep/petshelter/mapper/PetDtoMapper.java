package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.pets.PetDTO;
import com.ensep.petshelter.entities.Pet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetDtoMapper {

    PetDTO toPetDto(Pet pet);
    List<PetDTO> toPetDtoList(List<Pet> pets);
}
