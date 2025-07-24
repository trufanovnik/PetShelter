package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.user.UserLikedPetsDto;
import com.ensep.petshelter.dto.user.UserUpdateDTO;
import com.ensep.petshelter.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("@userSecurity.isOwnerOrAdmin(#id, authentication)")
    @PutMapping("/{id}")
    public ResponseEntity<UserLikedPetsDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO userUpdate,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(userService.updateUser(id, userUpdate, userDetails));
    }

    @PreAuthorize("@userSecurity.isOwnerOrAdmin(#id, authentication)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
