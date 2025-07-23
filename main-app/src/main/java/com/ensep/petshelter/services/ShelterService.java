package com.ensep.petshelter.services;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.shelter.ShelterDTO;
import com.ensep.petshelter.entities.AnimalKind;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.ShelterDtoMapper;
import com.ensep.petshelter.repositories.ShelterRepository;
import com.ensep.petshelter.specifications.ShelterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDtoMapper shelterDtoMapper;

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
    public void removeShelter(Long id, CustomUserDetails userDetails) {
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        String login = userDetails.getAccount().getLogin();
        Shelter shelter = shelterRepository.findByEmail(login);

        if (role.equals("ROLE_ADMIN") || id.equals(shelter.getId())) {
            shelterRepository.delete(shelter);
        }
    }
}
