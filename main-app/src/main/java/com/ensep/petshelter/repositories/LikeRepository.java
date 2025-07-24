package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.Like;
import com.ensep.petshelter.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByAccountAndPet(Account account, Pet pet);
}
