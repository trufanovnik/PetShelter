package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ShelterRepository extends
        JpaRepository<Shelter, Long>,
        JpaSpecificationExecutor<Shelter> {

    Shelter findByEmail(String email);

    default Shelter findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Приют с id " + id + " не найден."
                ));
    }
}
