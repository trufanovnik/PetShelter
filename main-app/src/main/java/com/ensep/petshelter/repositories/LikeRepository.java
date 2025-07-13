package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
