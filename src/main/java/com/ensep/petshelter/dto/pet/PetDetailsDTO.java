package com.ensep.petshelter.dto.pet;

import com.ensep.petshelter.dto.comment.CommentDTO;
import com.ensep.petshelter.dto.like.LikeDTO;
import com.ensep.petshelter.entities.AnimalKind;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PetDetailsDTO {
    private Long id;
    private String name;
    private String description;
    private AnimalKind animalKind;
    private LocalDateTime createdAt;
    private List<CommentDTO> petComments;
    private List<LikeDTO> petLikes;
}
