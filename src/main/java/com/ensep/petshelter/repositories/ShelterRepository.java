package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
