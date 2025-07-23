package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.entities.AnimalKind;
import com.ensep.petshelter.services.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteShelter(
            @PathVariable(name = "id") Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        shelterService.removeShelter(id, userDetails);
        return ResponseEntity.noContent().build();
    }
}
