package com.ensep.petshelter.config.security;

import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

    @Autowired
    private UserRepository userRepository;

    public boolean isOwnerOrAdmin(Long id, Authentication authentication) {
        System.out.println("user Id from request: " + id);
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("Not authenticated");
            return false;
        }
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        System.out.println("Role: " + role);
        if ("ROLE_ADMIN".equals(role)) {
            return true;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            String login = customUserDetails.getAccount().getLogin();
            System.out.println("Login from JWT: " + login);
            UserEntity user = userRepository.findByUsername(login);
            System.out.println("User from DB: " + user);
            if (user != null) {
                System.out.println("User id from DB: " + user.getId());
                return user.getId().equals(id);
            }
        }
        return false;
    }
}
