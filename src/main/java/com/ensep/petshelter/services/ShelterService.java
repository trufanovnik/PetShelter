package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.mapper.ShelterDtoMapper;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDtoMapper shelterDtoMapper;
    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;

    @Transactional(readOnly = true)
    public List<ShelterDTO> findAllShelters(){
        List<Shelter> shelters = shelterRepository.findAll();
        return shelterDtoMapper.toShelterDtoList(shelters);
    }

    @Transactional(readOnly = true)
    public ShelterDTO findById(Long id){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        return shelterDtoMapper.toShelterDto(shelter);
    }

    @Transactional
    public Shelter createShelter(Shelter shelter){
        return shelterRepository.save(shelter);
    }

    @Transactional
    public void removeShelter(Long id){
        shelterRepository.deleteById(id);
    }

    @Transactional
    public ShelterDTO updateShelter(Long id, Map<String, Object> updates){
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

    @Transactional(readOnly = true)
    public List<PetDTO> findAllPets(Long id){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        List<PetDTO> pets = petDtoMapper.toPetDtoList(shelter.getPets());
        return pets;
    }

    @Transactional
    public ShelterDTO addNewPet(Long id, PetDTO pet){
        Shelter shelter = shelterRepository.findById(id).orElseThrow();
        Pet newPet = new Pet();
        newPet.setName(pet.getName());
        newPet.setDescription(pet.getDescription());
        newPet.setShelter(shelter);
        newPet.setAnimalKind(pet.getAnimalKind());
        petRepository.save(newPet);
        return shelterDtoMapper.toShelterDto(shelter);
    }

    @Transactional
    public ResponseEntity<String> deletePetById(Long id, Long petId){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        Pet pet = petRepository.findById(petId).orElse(null);
        if (!shelter.getPets().contains(pet)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Данный питомец не принадлежит этому приюту.");
        }
        shelter.getPets().remove(pet);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public PetDTO updatePet(Long id, Long petId, Map<String, Object> updates){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        Pet pet = petRepository.findById(petId).orElse(null);

        updates.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Pet.class, fieldName);
            if (field != null){
                field.setAccessible(true);
                if (field.getType().isEnum() && fieldValue instanceof String) {
                    try {
                        Object enumValue = Enum.valueOf((Class<Enum>) field.getType(), ((String) fieldValue).toUpperCase());
                        ReflectionUtils.setField(field, pet, enumValue);
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Некорректное значение для поля " + fieldName + ": " + fieldValue, e);
                    }
                } else {
                    ReflectionUtils.setField(field, pet, fieldValue);
                }
            }
        });
        return petDtoMapper.toPetDto(petRepository.save(pet));
    }
}
