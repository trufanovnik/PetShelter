package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.shelter.ShelterUpdateDTO;
import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.Shelter;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShelterUpdateMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "email", source = "email")
    void updateShelterFromDto(ShelterUpdateDTO dto, @MappingTarget Shelter entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "login", source = "email")
    void updateAccountFromDto(ShelterUpdateDTO dto, @MappingTarget Account account);
}
