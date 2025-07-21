package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.shelter.ShelterRegistrationRequest;
import com.ensep.petshelter.entities.Shelter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShelterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pets", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(source = "title", target = "title")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "phoneNumber", target = "number")
    @Mapping(source = "email", target = "email")
    Shelter shelterRequestToShelter(ShelterRegistrationRequest request);
}
