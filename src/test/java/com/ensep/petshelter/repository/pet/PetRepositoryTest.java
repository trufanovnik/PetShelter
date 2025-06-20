package com.ensep.petshelter.repository.pet;

import com.ensep.petshelter.entities.*;
import com.ensep.petshelter.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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
    private UserEntity userEntity;
    private Shelter shelter;

    @BeforeEach
    void init(){
        shelter = new Shelter();
        shelter.setTitle("Happy Pet Shelter");
        shelter.setCity("Minsk");
        shelter = shelterRepository.save(shelter);

        userEntity = new UserEntity();
        userEntity.setName("Igor");
        userEntity.setEmail("email@email.com");
        userEntity.setUsername("igor123");
        userEntity.setPassword("123");
        userEntity = userRepository.save(userEntity);

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
        userEntity = userRepository.findById(userEntity.getId()).orElseThrow();

        Comment comment = new Comment();
        comment.setContent("Lovely pet!");
        comment.setAuthor(userEntity);
        comment.setPet(pet);
        commentRepository.save(comment);

        entityManager.flush();
        entityManager.clear();

        Comment savedComment = commentRepository.findAll().get(0);

        commentRepository.delete(savedComment);
        entityManager.flush();
        entityManager.clear();

        Pet updatedPet = petRepository.findById(pet.getId()).orElseThrow();
        UserEntity updatedUserEntity = userRepository.findById(userEntity.getId()).orElseThrow();

        assertThat(updatedPet.getComments()).isEmpty();
        assertThat(updatedUserEntity.getComments()).isEmpty();
    }
}