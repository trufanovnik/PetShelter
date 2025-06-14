package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.entities.Shelter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelterDtoMapper {

    ShelterDto toShelterDto(Shelter shelter);
    List<ShelterDto> toShelterDtoList(List<Shelter> shelters);
}
