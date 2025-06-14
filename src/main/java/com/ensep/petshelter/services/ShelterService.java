package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.ShelterDtoMapper;
import com.ensep.petshelter.repositories.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDtoMapper shelterDtoMapper;

    public ShelterService(ShelterRepository shelterRepository, ShelterDtoMapper shelterDtoMapper) {
        this.shelterRepository = shelterRepository;
        this.shelterDtoMapper = shelterDtoMapper;
    }

    public List<ShelterDto> findAllShelters(){
        List<Shelter> shelters = shelterRepository.findAll();
        return shelterDtoMapper.toShelterDtoList(shelters);
    }

    public ShelterDto findById(Long id){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        return shelterDtoMapper.toShelterDto(shelter);
    }
}
