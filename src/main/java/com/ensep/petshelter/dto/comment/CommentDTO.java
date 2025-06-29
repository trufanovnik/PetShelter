package com.ensep.petshelter.dto.comment;

import com.ensep.petshelter.dto.shelter.ShelterCommentsDTO;
import com.ensep.petshelter.dto.user.UserCommentsLikesDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserCommentsLikesDTO author;
    private ShelterCommentsDTO shelter;
}
