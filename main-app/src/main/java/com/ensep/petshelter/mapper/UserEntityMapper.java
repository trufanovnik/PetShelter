package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.UserRegistrationRequest;
import com.ensep.petshelter.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(source = "username", target = "username")
    UserEntity userRequestToUserEntity(UserRegistrationRequest request);
}
