package com.ensep.petshelter.mapper;

import com.ensep.petshelter.dto.account.AccountCommentLikeDTO;
import com.ensep.petshelter.dto.comment.CommentDTO;
import com.ensep.petshelter.dto.like.LikeDTO;
import com.ensep.petshelter.dto.pets.PetDTO;
import com.ensep.petshelter.dto.pets.PetDetailsDTO;
import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.Comment;
import com.ensep.petshelter.entities.Like;
import com.ensep.petshelter.entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetDtoMapper {

    PetDTO toPetDto(Pet pet);
    List<PetDTO> toPetDtoList(List<Pet> pets);

    AccountCommentLikeDTO toAccountCommentLikeDto(Account account);

    @Mapping(target = "author", source = "account")
    CommentDTO toCommentDTO(Comment comment);

    @Mapping(target = "whoLiked", source = "account")
    LikeDTO toLikeDTO(Like like);

    @Mapping(target = "petLikes", source = "likes")
    @Mapping(target = "petComments", source = "comments")
    PetDetailsDTO toPetDetailsDTO(Pet pet);
}
