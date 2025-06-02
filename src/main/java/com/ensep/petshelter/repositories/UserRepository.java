package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.favoritePets FROM User u WHERE u.id = :userId")
    List<Pet> findFavoritePetsByUserId(@Param("userId") Long userId);

}
