package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.pets.PetDTO;
import com.ensep.petshelter.dto.user.UserLikedPetsDto;
import com.ensep.petshelter.entities.Like;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UserLikedPetsMapper {

    @Mapping(source = "account.login", target = "username")
    @Mapping(source = "account.likes", target = "likedPets", qualifiedByName = "mapLikesToPetDto")
    UserLikedPetsDto toDto(UserEntity user);

    @Named("mapLikesToPetDto")
    default List<PetDTO> mapLikesToPetDto(List<Like> likes) {
        if (likes == null) return Collections.emptyList();

        return likes.stream()
                .map(Like::getPet)
                .filter(Objects::nonNull)
                .map(this::toPetDto)
                .toList();
    }

    PetDTO toPetDto(Pet pet);
}
