package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
