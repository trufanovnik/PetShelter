package com.ensep.petshelter.repository.pet;

import com.ensep.petshelter.entities.*;
import com.ensep.petshelter.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private LikeRepository likeRepository;

    private User testUser;
    private Shelter testShelter;
    private Pet testPet;
    private Comment testComment;
    private Like testLike;

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
        userRepository.save(testUser);

        testPet = new Pet();
        testPet.setName("Zodiac");
        testPet.setDescription("Funny boy");
        testPet.setAnimal_kind(AnimalKind.DOG);
        testPet.setShelter(testShelter);
        testPet.getPhotoUrls().addAll(List.of("photo1.jpg", "photo2.jpg"));
        petRepository.save(testPet);

        testComment = new Comment();
        testComment.setContent("Great pet!");
        testComment.setAuthor(testUser);
        testComment.setPet(testPet);
        testComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(testComment);

        testLike = new Like();
        testLike.setUser(testUser);
        testLike.setPet(testPet);
        likeRepository.save(testLike);

        entityManager.flush();
    }

    @Test
    void whenFindById_thenReturnPetWithAllRelations() {
        Pet found = petRepository.findById(testPet.getId()).orElseThrow();

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Zodiac");
        assertThat(found.getShelter()).isEqualTo(testShelter);
        assertThat(found.getPhotoUrls()).containsExactly("photo1.jpg", "photo2.jpg");
        assertThat(found.getLikes()).isEmpty();
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

        testPet.getComments().add(newComment);

        commentRepository.save(newComment);
        petRepository.save(testPet);

        entityManager.flush();
        entityManager.clear();

        Pet updatedPet = petRepository.findById(testPet.getId()).orElseThrow();
        assertThat(updatedPet.getComments())
                .hasSize(2)
                .extracting(Comment::getContent)
                .containsExactlyInAnyOrder("Great pet!", "New comment");
    }

    @Test
    void whenRemoveComment_thenPetCommentsAndUserCommentsCollectionUpdated(){
        Pet updatedPet = petRepository.findById(testPet.getId()).orElseThrow();
        commentRepository.delete(testComment);
        assertThat(updatedPet.getComments())
                .hasSize(0);
        assertThat(testUser.getComments())
                .hasSize(0);
    }

    @Test
    void whenRemoveComment_thenPetCommentsAndShelterCommentsCollectionUpdated(){
        Pet updatedPet = petRepository.findById(testPet.getId()).orElseThrow();
        commentRepository.delete(testComment);
        assertThat(updatedPet.getComments())
                .hasSize(0);
        assertThat(testShelter.getComments())
                .hasSize(0);
    }

    @Test
    void whenDeletePet_thenCommentsDeleted(){
        petRepository.delete(testPet);
        assertThat(testUser.getComments()).isEmpty();
    }

    @Test
    void whenPetGetLike_thenPetAndUserUpdated(){
        Like testLike = new Like();
        testLike.setPet(testPet);
        testLike.setUser(testUser);
        testPet.getLikes().add(testLike);

        Pet updatedPet = petRepository.findById(testPet.getId()).orElseThrow();
        assertThat(updatedPet.getLikes()).contains(testLike);
    }
}