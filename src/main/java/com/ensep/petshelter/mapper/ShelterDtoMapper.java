package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.ShelterDTO;
import com.ensep.petshelter.entities.Shelter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PetDtoMapper.class })
public interface ShelterDtoMapper {

    ShelterDTO toShelterDto(Shelter shelter);
    List<ShelterDTO> toShelterDtoList(List<Shelter> shelters);
}
