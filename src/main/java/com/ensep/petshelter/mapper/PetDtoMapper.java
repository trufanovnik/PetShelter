package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.PetDto;
import com.ensep.petshelter.entities.Pet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetDtoMapper {

    PetDto toPetDto(Pet pet);
    List<PetDto> toPetDtoList(List<Pet> pets);
}
