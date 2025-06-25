package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.comment.CommentDTO;
import com.ensep.petshelter.dto.like.LikeDTO;
import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.pet.PetDetailsDTO;
import com.ensep.petshelter.dto.shelter.ShelterCommentsDTO;
import com.ensep.petshelter.dto.user.UserCommentsLikesDTO;
import com.ensep.petshelter.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetDtoMapper {

    PetDTO toPetDto(Pet pet);
    List<PetDTO> toPetDtoList(List<Pet> pets);

    UserCommentsLikesDTO toUserCommentsLikesDTO(UserEntity user);
    ShelterCommentsDTO toShelterCommentsDTO(Shelter shelter);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "shelter", source = "shelter")
    CommentDTO toCommentDTO(Comment comment);

    @Mapping(target = "userWhoLiked", source = "userEntity")
    LikeDTO toLikeDTO(Like like);

    @Mapping(target = "petComments", source = "comments")
    @Mapping(target = "petLikes", source = "likes")
    PetDetailsDTO toPetDetailsDTO(Pet pet);
}
