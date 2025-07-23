package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.pets.PetDTO;
import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.dto.shelter.ShelterUpdateDTO;
import com.ensep.petshelter.entities.AnimalKind;
import com.ensep.petshelter.services.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelter")
@RequiredArgsConstructor
public class ShelterController {

    private final ShelterService shelterService;

    @GetMapping("/all")
    public ResponseEntity<Page<ShelterDTO>> findAllShelters(
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "animalKind", required = false) AnimalKind animalKind,
            @PageableDefault(
                    size = 10,
                    sort = "title",
                    direction = Sort.Direction.ASC)
                    Pageable pageable
            ) {
        return ResponseEntity.ok(shelterService.findAllShelters(city, animalKind, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDTO> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(shelterService.findById(id));
    }

    @PreAuthorize("@shelterSecurity.isOwnerOrAdmin(#id, authentication)")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable(name = "id") Long id) {
        shelterService.removeShelter(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("@shelterSecurity.isOwnerOrAdmin(#id, authentication)")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ShelterUpdateDTO> updateShelter(
            @PathVariable(name = "id") Long id,
            @RequestBody ShelterUpdateDTO shelterUpdate,
            @AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(shelterService.updateShelter(id, shelterUpdate, userDetails));
    }

    @PreAuthorize("@shelterSecurity.isOwnerOrAdmin(#id, authentication)")
    @PostMapping(value = "/{id}/pets/addPet")
    public ResponseEntity<ShelterDTO> addNewPet(
            @PathVariable(name = "id") Long id,
            @RequestBody PetDTO pet) {
        return ResponseEntity.ok(shelterService.addNewPet(id, pet));
    }

    @PreAuthorize("@shelterSecurity.isOwnerOrAdmin(#id, authentication)")
    @PutMapping(value = "/{id}/pets/{petId}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long id,
                                            @PathVariable Long petId,
                                            @RequestBody PetDTO petUpdate) {
        return ResponseEntity.ok(shelterService.updatePet(petId, petUpdate));
    }

}
