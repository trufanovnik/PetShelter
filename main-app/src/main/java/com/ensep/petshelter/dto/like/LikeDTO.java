package com.ensep.petshelter.dto.like;

import com.ensep.petshelter.dto.account.AccountCommentLikeDTO;
import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private AccountCommentLikeDTO whoLiked;
}
