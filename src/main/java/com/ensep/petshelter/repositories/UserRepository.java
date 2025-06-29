package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
