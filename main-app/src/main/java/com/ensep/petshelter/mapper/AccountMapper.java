package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.shelter.ShelterRegistrationRequest;
import com.ensep.petshelter.dto.auth.UserRegistrationRequest;
import com.ensep.petshelter.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    @Mapping(source = "email", target = "login")
    Account shelterRequestToAccount(ShelterRegistrationRequest request);

    @Mapping(source = "username", target = "login")
    @Mapping(target = "password", ignore = true)
    Account userRequestToAccount(UserRegistrationRequest request);
}
