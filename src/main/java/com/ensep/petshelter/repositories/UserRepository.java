package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    default UserEntity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Пользователь с id " + id + " не найден."
                ));
    }
}
