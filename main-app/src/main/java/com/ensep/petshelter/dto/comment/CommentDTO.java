package com.ensep.petshelter.dto.comment;

import com.ensep.petshelter.dto.account.AccountCommentLikeDTO;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private AccountCommentLikeDTO author;
    private String content;
}
