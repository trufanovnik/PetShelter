package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.pets.PetDetailsDTO;
import com.ensep.petshelter.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/{id}")
    public ResponseEntity<PetDetailsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(petService.findById(id));
    }
}
