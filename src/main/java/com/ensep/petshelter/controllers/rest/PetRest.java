package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.services.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet")
public class PetRest {

    private final PetService petService;

    public PetRest(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllPets(){
        return ResponseEntity.ok(petService.findAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(petService.findById(id));
    }
}
