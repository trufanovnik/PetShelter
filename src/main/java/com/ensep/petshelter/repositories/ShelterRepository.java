package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findById(Long id);

    @Override
    void deleteById(Long id);
}
