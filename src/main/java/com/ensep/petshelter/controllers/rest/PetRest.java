package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.entities.Comment;
import com.ensep.petshelter.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pet")
public class PetRest {

    private final PetService petService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllPets(){
        return ResponseEntity.ok(petService.findAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(petService.findById(id));
    }

    @PostMapping("/{id}/addComment")
    public ResponseEntity<?> addNewComment(@PathVariable Long id,
                                           @RequestBody Comment comment){
        return ResponseEntity.ok(petService.addComment(id, comment));
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id,
                                               @PathVariable Long commentId){
        return petService.deleteCommentById(id, commentId);
    }
}
