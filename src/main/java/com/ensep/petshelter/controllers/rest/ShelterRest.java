package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.services.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shelter")
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
    public ResponseEntity<ShelterDto> updateShelter(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(shelterService.updateShelter(id, updates));
    }
}
