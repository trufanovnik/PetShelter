package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.dto.shelter.ShelterUpdateDTO;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import com.ensep.petshelter.services.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shelter")
public class ShelterRest {

    private final ShelterService shelterService;
    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ShelterDTO>> findAllShelters(){
        return ResponseEntity.ok(shelterService.findAllShelters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(shelterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Shelter> createShelter(@RequestBody Shelter shelter){
        return ResponseEntity.ok(shelterService.createShelter(shelter));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable Long id){
        shelterService.removeShelter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ShelterUpdateDTO> updateShelter(@PathVariable Long id,
                                                          @RequestBody ShelterUpdateDTO shelterUpdate){
        return ResponseEntity.ok(shelterService.updateShelter(id, shelterUpdate));
    }

    @GetMapping(value = "/{id}/pets")
    public ResponseEntity<List<PetDTO>> findAllPets(@PathVariable Long id){
        return ResponseEntity.ok(shelterService.findAllPets(id));
    }

    @PostMapping(value = "/{id}/pets/addPet")
    public ResponseEntity<ShelterDTO> addNewPet(@PathVariable Long id, @RequestBody PetDTO pet){
        return ResponseEntity.ok(shelterService.addNewPet(id, pet));
    }

    @PutMapping(value = "/{id}/pets/{petId}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long id,
                                       @PathVariable Long petId,
                                       @RequestBody PetDTO petUpdate) {
        return ResponseEntity.ok(shelterService.updatePet(id, petId, petUpdate));
    }

    @DeleteMapping(value = "/{id}/pets/{petId}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id,
                                           @PathVariable Long petId) {
        return shelterService.deletePetById(id, petId);
    }
}
