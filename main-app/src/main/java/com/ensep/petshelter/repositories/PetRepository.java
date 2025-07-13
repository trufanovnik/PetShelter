package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByShelterId(Long shelter_id);

    default Pet findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Питомец с id " + id + " не найден."
                ));
    }
}
