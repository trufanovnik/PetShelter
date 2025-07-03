package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.user.UserEntityUpdateDTO;
import com.ensep.petshelter.entities.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserEntityUpdateMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    void updateUserFromDto(UserEntityUpdateDTO dto, @MappingTarget UserEntity userUpdate);
}
