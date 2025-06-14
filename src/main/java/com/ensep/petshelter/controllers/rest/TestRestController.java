package com.ensep.petshelter.controllers.rest;

import com.ensep.petshelter.dto.PetDto;
import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.repositories.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestRestController {

}
