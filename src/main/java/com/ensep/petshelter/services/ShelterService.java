package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.PetDto;
import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.ShelterDtoMapper;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;
    private final ShelterDtoMapper shelterDtoMapper;

    public ShelterService(ShelterRepository shelterRepository, PetRepository petRepository, ShelterDtoMapper shelterDtoMapper) {
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
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

    public Shelter createShelter(Shelter shelter){
        return shelterRepository.save(shelter);
    }

    public void removeShelter(Long id){
        shelterRepository.deleteById(id);
    }

    public ShelterDto updateShelter(Long id, Map<String, Object> updates){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        updates.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Shelter.class, fieldName);
            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, shelter, fieldValue);
            }
        });
        return shelterDtoMapper.toShelterDto(shelterRepository.save(shelter));
    }

    public ShelterDto addNewPet(Long id, PetDto pet){
        Shelter shelter = shelterRepository.findById(id).orElseThrow();
        Pet newPet = new Pet();
        newPet.setName(pet.getName());
        newPet.setDescription(pet.getDescription());
        newPet.setShelter(shelter);
        newPet.setAnimalKind(pet.getAnimalKind());
        petRepository.save(newPet);
        return shelterDtoMapper.toShelterDto(shelter);
    }
}
