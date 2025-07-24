package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.pets.PetDetailsDTO;
import com.ensep.petshelter.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/{id}")
    public ResponseEntity<PetDetailsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(petService.findById(id));
    }


    @PostMapping("/{id}/addComment")
    public ResponseEntity<PetDetailsDTO> addNewComment(@PathVariable Long id,
                                                       @RequestBody String content,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(petService.addComment(id, content, userDetails));
    }

    @PreAuthorize("@petSecurity.isOwnerOrAdmin(#commentId, authentication)")
    @DeleteMapping("/{id}/deleteComment/{commentId}")
    public ResponseEntity<PetDetailsDTO> deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        return ResponseEntity.ok(petService.deleteComment(id, commentId));
    }
}
