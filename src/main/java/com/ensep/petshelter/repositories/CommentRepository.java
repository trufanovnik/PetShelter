package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Comment;
import com.ensep.petshelter.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthorId(Long author_id);
    List<Comment> findByShelterId(Long shelter_id);
    List<Comment> findByPetId(Long pet_id);

    default Comment findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Комментарий с id " + id + " не найден."
                ));
    }
}
