package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.dto.shelter.ShelterUpdateDTO;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.mapper.PetUpdateMapper;
import com.ensep.petshelter.mapper.ShelterDtoMapper;
import com.ensep.petshelter.mapper.ShelterUpdateMapper;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDtoMapper shelterDtoMapper;
    private final ShelterUpdateMapper shelterUpdateMapper;
    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;
    private final PetUpdateMapper petUpdateMapper;

    @Transactional(readOnly = true)
    public List<ShelterDTO> findAllShelters(){
        List<Shelter> shelters = shelterRepository.findAll();
        return shelterDtoMapper.toShelterDtoList(shelters);
    }

    @Transactional(readOnly = true)
    public ShelterDTO findById(Long id){
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
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
    public ShelterUpdateDTO updateShelter(Long id, ShelterUpdateDTO shelterUpdate){
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
        shelterUpdateMapper.updateShelterFromDto(shelterUpdate, shelter);
        return shelterDtoMapper.toShelterUpdateDto(shelterRepository.save(shelter));
    }

    @Transactional(readOnly = true)
    public List<PetDTO> findAllPets(Long id){
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
        List<PetDTO> pets = petDtoMapper.toPetDtoList(shelter.getPets());
        return pets;
    }

    @Transactional
    public ShelterDTO addNewPet(Long id, PetDTO pet){
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
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
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
        Pet pet = petRepository.findByIdOrThrow(petId);
        if (!shelter.getPets().contains(pet)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Данный питомец не принадлежит этому приюту.");
        }
        shelter.getPets().remove(pet);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public PetDTO updatePet(Long id, Long petId, PetDTO petUpdate){
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
        Pet pet = petRepository.findByIdOrThrow(petId);

        petUpdateMapper.updatePetFromDto(petUpdate, pet);
        return petDtoMapper.toPetDto(petRepository.save(pet));
    }
}
