package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.user.UserEntityDTO;
import com.ensep.petshelter.entities.Like;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { PetDtoMapper.class })
public interface UserEntityDtoMapper {

    @Mapping(target = "likedPets", source = "likes", qualifiedByName = "likesToPets")
    UserEntityDTO userEntityToUserEntityDto(UserEntity userEntity);

    @Named("likesToPets")
    default List<PetDTO> mapLikesToPetDTOs(List<Like> likes){
        if (likes == null) return Collections.emptyList();

        return likes.stream()
                .map(Like::getPet)
                .map(this::toPetDto)
                .collect(Collectors.toList());
    }

    PetDTO toPetDto(Pet pet);
}
