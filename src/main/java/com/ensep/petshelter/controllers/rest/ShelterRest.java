package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.services.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterRest {

    private final ShelterService shelterService;

    public ShelterRest(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShelterDto>> findAllShelters(){
        return ResponseEntity.ok(shelterService.findAllShelters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(shelterService.findById(id));
    }
}
