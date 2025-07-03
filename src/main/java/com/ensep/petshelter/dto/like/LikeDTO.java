package com.ensep.petshelter.dto.like;

import com.ensep.petshelter.dto.user.UserCommentsLikesDTO;
import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private UserCommentsLikesDTO userWhoLiked;
}
