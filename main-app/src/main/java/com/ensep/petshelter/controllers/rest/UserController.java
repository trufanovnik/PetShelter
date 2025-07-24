package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.user.UserLikedPetsDto;
import com.ensep.petshelter.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("@userSecurity.isOwnerOrAdmin(#id, authentication)")
    @GetMapping("/{id}")
    public ResponseEntity<UserLikedPetsDto> findUserByIdWithLikedPets(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserLikedPets(id));
    }
}
