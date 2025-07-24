package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    default UserEntity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Пользователь с id " + id + " не найден."
                ));
    }

    @EntityGraph(attributePaths = {"account.likes.pet"})
    Optional<UserEntity> findWithLikedPetsById(Long userId);

    UserEntity findByUsername(String username);
}
