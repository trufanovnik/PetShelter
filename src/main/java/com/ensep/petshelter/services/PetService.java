package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.pet.PetDetailsDTO;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;

    public PetService(PetRepository petRepository, PetDtoMapper petDtoMapper) {
        this.petRepository = petRepository;
        this.petDtoMapper = petDtoMapper;
    }

    public List<PetDTO> findAllPets(){
        List<Pet> pets = petRepository.findAll();
        return petDtoMapper.toPetDtoList(pets);
    }

    public PetDetailsDTO findById(Long id){
        Pet pet = petRepository.findById(id).orElse(null);
        return petDtoMapper.toPetDetailsDTO(pet);
    }
}
