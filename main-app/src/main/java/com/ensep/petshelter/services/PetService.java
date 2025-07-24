package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.pets.PetDetailsDTO;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;

    @Transactional(readOnly = true)
    public PetDetailsDTO findById(Long id) {
        Pet pet = petRepository.findByIdOrThrow(id);

        return petDtoMapper.toPetDetailsDTO(pet);
    }
}
