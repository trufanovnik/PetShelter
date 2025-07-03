package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.entities.Pet;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PetUpdateMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "animalKind", source = "animalKind")
    void updatePetFromDto(PetDTO dto, @MappingTarget Pet pet);
}
