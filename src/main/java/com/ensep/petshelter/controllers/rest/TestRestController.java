package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.PetDto;
import com.ensep.petshelter.entities.AnimalKind;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class TestRestController {

    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;

    public TestRestController(PetRepository petRepository, PetDtoMapper petDtoMapper) {
        this.petRepository = petRepository;
        this.petDtoMapper = petDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> findAll(){
        List<Pet> pets = petRepository.findAll();
        System.out.println("Pets from DB: " + pets);
        List<PetDto> petsDtoList = petDtoMapper.toPetDtoList(pets);
        return ResponseEntity.ok(petsDtoList);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API works!");
    }
}
