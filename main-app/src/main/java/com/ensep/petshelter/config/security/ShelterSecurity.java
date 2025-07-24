package com.ensep.petshelter.config.security;

import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.repositories.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("shelterSecurity")
public class ShelterSecurity {

    @Autowired
    private ShelterRepository shelterRepository;

    public boolean isOwnerOrAdmin(Long id, Authentication authentication) {
        System.out.println("shelterId from request: " + id);
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
            Shelter shelter = shelterRepository.findByEmail(login);
            System.out.println("Shelter from DB: " + shelter);
            if (shelter != null) {
                System.out.println("Shelter id from DB: " + shelter.getId());
                return shelter.getId().equals(id);
            }
        }
        return false;
    }
}
