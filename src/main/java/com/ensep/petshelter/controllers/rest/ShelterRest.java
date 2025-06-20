package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.PetDto;
import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import com.ensep.petshelter.services.ShelterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/shelter")
public class ShelterRest {

    private final ShelterService shelterService;
    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;

    public ShelterRest(ShelterService shelterService, ShelterRepository shelterRepository, PetRepository petRepository) {
        this.shelterService = shelterService;
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShelterDto>> findAllShelters(){
        return ResponseEntity.ok(shelterService.findAllShelters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDto> findById(@PathVariable Long id){
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

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateShelter(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Приют с ID: " + id + " не найден");
        }
        return ResponseEntity.ok(shelterService.updateShelter(id, updates));
    }

    @GetMapping(value = "/{id}/allPets")
    public ResponseEntity<?> findAllPets(@PathVariable Long id){
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Приют с ID: " + id + " не найден");
        }
        return ResponseEntity.ok(shelterService.findAllPets(id));
    }

    @PostMapping(value = "/{id}/addPet")
    public ResponseEntity<?> addNewPet(@PathVariable Long id, @RequestBody PetDto pet){
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Приют с ID: " + id + " не найден");
        }
        return ResponseEntity.ok(shelterService.addNewPet(id, pet));
    }

    @PatchMapping(value = "/{id}/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable Long id,
                                       @PathVariable Long petId,
                                       @RequestBody Map<String, Object> updates) {
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Приют с ID: " + id + " не найден");
        }
        Optional<Pet> pet = petRepository.findById(petId);
        if (pet.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Питомец с ID: " + petId + " не найден");
        }
        return ResponseEntity.ok(shelterService.updatePet(id, petId, updates));
    }
}
