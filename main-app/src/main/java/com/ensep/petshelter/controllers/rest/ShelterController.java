package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.entities.AnimalKind;
import com.ensep.petshelter.services.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
