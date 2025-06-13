package com.ensep.petshelter.repository.pet;

import com.ensep.petshelter.entities.*;
import com.ensep.petshelter.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    private Pet pet;
    private User user;
    private Shelter shelter;

    @BeforeEach
    void init(){
        shelter = new Shelter();
        shelter.setTitle("Happy Pet Shelter");
        shelter.setCity("Minsk");
        shelter = shelterRepository.save(shelter);

        user = new User();
        user.setName("Igor");
        user.setEmail("email@email.com");
        user.setUsername("igor123");
        user.setPassword("123");
        user = userRepository.save(user);

        pet = new Pet();
        pet.setName("Zodiac");
        pet.setDescription("Funny boy");
        pet.setAnimalKind(AnimalKind.DOG);
        pet.setShelter(shelter);
        pet.getPhotoUrls().addAll(List.of("photo1.jpg", "photo2.jpg"));
        pet = petRepository.save(pet);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void testDeletingCommentUpdatesOwnerCollections() {
        pet = petRepository.findById(pet.getId()).orElseThrow();
        user = userRepository.findById(user.getId()).orElseThrow();

        Comment comment = new Comment();
        comment.setContent("Lovely pet!");
        comment.setAuthor(user);
        comment.setPet(pet);
        commentRepository.save(comment);

        entityManager.flush();
        entityManager.clear();

        Comment savedComment = commentRepository.findAll().get(0);

        commentRepository.delete(savedComment);
        entityManager.flush();
        entityManager.clear();

        Pet updatedPet = petRepository.findById(pet.getId()).orElseThrow();
        User updatedUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(updatedPet.getComments()).isEmpty();
        assertThat(updatedUser.getComments()).isEmpty();
    }
}