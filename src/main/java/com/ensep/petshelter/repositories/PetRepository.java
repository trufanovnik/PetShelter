package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByShelterId(Long shelter_id);
    List<Pet> findByUserId(Long user_id);
}
