package com.ensep.petshelter.repository.pet;

import com.ensep.petshelter.entities.*;
import com.ensep.petshelter.repositories.CommentRepository;
import com.ensep.petshelter.repositories.PetRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import com.ensep.petshelter.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    private User testUser;
    private Shelter testShelter;
    private Pet testPet;
    private Comment testComment;

    @BeforeEach
    void init(){
        testShelter = new Shelter();
        testShelter.setTitle("Happy Pet Shelter");
        testShelter.setCity("Minsk");
        shelterRepository.save(testShelter);

        testUser = new User();
        testUser.setName("Igor");
        testUser.setEmail("email@email.com");
        testUser.setUsername("igor123");
        testUser.setPassword("123");
        entityManager.persist(testUser);

        testPet = new Pet();
        testPet.setName("Zodiac");
        testPet.setDescription("Funny boy");
        testPet.setAnimal_kind(AnimalKind.DOG);
        testPet.setShelter(testShelter);
        testPet.getPhotoUrls().addAll(List.of("photo1.jpg", "photo2.jpg"));
        testPet.getFavoritedByUsers().add(testUser);
        entityManager.persist(testPet);

        testComment = new Comment();
        testComment.setContent("Great pet!");
        testComment.setAuthor(testUser);
        testComment.setPet(testPet);
        testComment.setCreatedAt(LocalDateTime.now());
        testPet.getComments().add(testComment);
        entityManager.persist(testComment);

        entityManager.flush();
    }

    @Test
    void whenFindById_thenReturnPetWithAllRelations() {
        Pet found = petRepository.findById(testPet.getId()).orElseThrow();

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Zodiac");
        assertThat(found.getShelter()).isEqualTo(testShelter);
        assertThat(found.getPhotoUrls()).containsExactly("photo1.jpg", "photo2.jpg");
        assertThat(found.getFavoritedByUsers()).hasSize(1).contains(testUser);
    }

    @Test
    void whenCreatePetWithoutShelter_thenThrowException() {
        Pet pet = new Pet();
        pet.setName("No Shelter Pet");

        assertThrows(jakarta.persistence.PersistenceException.class, () -> {
            entityManager.persistAndFlush(pet);
        });
    }

    @Test
    void whenAddComment_thenPetCommentsCollectionUpdated() {
        Comment newComment = new Comment();
        newComment.setContent("New comment");
        newComment.setAuthor(testUser);
        newComment.setPet(testPet);
        newComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(newComment);
        testPet.getComments().add(newComment);

        Pet updatedPet = petRepository.findById(testPet.getId()).orElseThrow();
        assertThat(updatedPet.getComments())
                .hasSize(2)
                .extracting(Comment::getContent)
                .containsExactlyInAnyOrder("Great pet!", "New comment");
    }
}
