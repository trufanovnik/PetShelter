package com.ensep.petshelter.services;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.pets.PetDTO;
import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.dto.shelter.ShelterUpdateDTO;
import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.AnimalKind;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.ShelterDtoMapper;
import com.ensep.petshelter.mapper.ShelterUpdateMapper;
import com.ensep.petshelter.repositories.AccountRepository;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import com.ensep.petshelter.specifications.ShelterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDtoMapper shelterDtoMapper;
    private final ShelterUpdateMapper shelterUpdateMapper;
    private final AccountRepository accountRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public Page<ShelterDTO> findAllShelters(String city, AnimalKind animalKind, Pageable pageable) {
        Specification<Shelter> spec = ShelterSpecification.distinct()
                .and(ShelterSpecification.cityContains(city))
                .and(ShelterSpecification.hasAnimalKind(animalKind));

        Page<Shelter> shelters = shelterRepository.findAll(spec, pageable);
        return shelters.map(shelterDtoMapper::toShelterDto);
    }

    @Transactional(readOnly = true)
    public ShelterDTO findById(Long id){
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
        return shelterDtoMapper.toShelterDto(shelter);
    }

    @Transactional
    public void removeShelter(Long id) {
        Shelter shelter = shelterRepository.findByIdOrThrow(id);
        shelterRepository.delete(shelter);
    }

    @Transactional
    public ShelterUpdateDTO updateShelter(Long id, ShelterUpdateDTO shelterUpdate, CustomUserDetails userDetails) {
        String login = userDetails.getAccount().getLogin();

        Shelter shelter = shelterRepository.findByEmail(login);
        Account account = accountRepository.findByLoginOrThrow(login);

        shelterUpdateMapper.updateShelterFromDto(shelterUpdate, shelter);
        shelterUpdateMapper.updateAccountFromDto(shelterUpdate, account);

        return shelterDtoMapper.toShelterUpdateDto(shelterRepository.save(shelter));
    }

    @Transactional
    public ShelterDTO addNewPet(Long id, PetDTO pet) {

        Shelter shelter = shelterRepository.findByIdOrThrow(id);
        Pet newPet = new Pet();
        newPet.setName(pet.getName());
        newPet.setDescription(pet.getDescription());
        newPet.setAnimalKind(pet.getAnimalKind());
        newPet.setShelter(shelter);
        petRepository.save(newPet);
        return shelterDtoMapper.toShelterDto(shelter);
    }
}
