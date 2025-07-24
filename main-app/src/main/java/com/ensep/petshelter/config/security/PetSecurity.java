package com.ensep.petshelter.config.security;

import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.Comment;
import com.ensep.petshelter.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("petSecurity")
@RequiredArgsConstructor
public class PetSecurity {

    private final CommentRepository commentRepository;

    public boolean isOwnerOrAdmin(Long commentId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if ("ROLE_ADMIN".equals(role)) {
            return true;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            Account account = customUserDetails.getAccount();
            String login = account.getLogin();
            Comment comment = commentRepository.findByIdOrThrow(commentId);

            if (account != null) {
                return comment.getAccount().getLogin().equals(login);
            }
        }
        return false;
    }
}
