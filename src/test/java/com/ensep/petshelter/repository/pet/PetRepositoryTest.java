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
        pet.setAnimal_kind(AnimalKind.DOG);
        pet.setShelter(shelter);
        pet.getPhotoUrls().addAll(List.of("photo1.jpg", "photo2.jpg"));
        pet = petRepository.save(pet);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void testAddingCommentUpdatesAuthorAndPetCollections() {
        // Получаем свежие экземпляры сущностей из базы, чтобы они были attached к persistence context
        pet = petRepository.findById(pet.getId()).orElseThrow();
        user = userRepository.findById(user.getId()).orElseThrow();

        // Создаем комментарий и устанавливаем связи
        Comment comment = new Comment();
        comment.setContent("Nice pet!");
        comment.setAuthor(user);
        comment.setPet(pet);

        // Обновляем двунаправленные коллекции
        pet.getComments().add(comment);
        user.getComments().add(comment);

        // Сохраняем питомца, что благодаря каскадам сохранит и комментарий
        petRepository.save(pet);
        entityManager.flush();
        entityManager.clear();

        // Извлекаем сущности заново для проверки актуального состояния
        Pet savedPet = petRepository.findById(pet.getId()).orElseThrow();
        User savedUser = userRepository.findById(user.getId()).orElseThrow();

        // Проверяем, что питомец содержит ровно 1 комментарий
        assertThat(savedPet.getComments()).hasSize(1);
        // И затем, что в комментарии содержится правильное содержимое
        Comment savedComment = savedPet.getComments().get(0);
        assertThat(savedComment.getContent()).isEqualTo("Nice pet!");

        // Проверяем, что список комментариев у пользователя также содержит только данный комментарий
        assertThat(savedUser.getComments()).hasSize(1);
        assertThat(savedUser.getComments().get(0).getContent()).isEqualTo("Nice pet!");
    }

    @Test
    public void testDeletingCommentUpdatesOwnerCollections() {
        // Сначала добавляем комментарий
        pet = petRepository.findById(pet.getId()).orElseThrow();
        user = userRepository.findById(user.getId()).orElseThrow();

        Comment comment = new Comment();
        comment.setContent("Lovely pet!");
        comment.setAuthor(user);
        comment.setPet(pet);

        pet.getComments().add(comment);
        user.getComments().add(comment);

        petRepository.save(pet);
        entityManager.flush();
        entityManager.clear();

        // Извлекаем комментарий для удаления
        Comment savedComment = commentRepository.findAll().get(0);

        // Удаляем комментарий через репозиторий
        commentRepository.delete(savedComment);
        entityManager.flush();
        entityManager.clear();

        // Извлекаем питомца и пользователя повторно
        Pet updatedPet = petRepository.findById(pet.getId()).orElseThrow();
        User updatedUser = userRepository.findById(user.getId()).orElseThrow();

        // Проверяем, что список комментариев у питомца пустой
        assertThat(updatedPet.getComments()).isEmpty();
        // Аналогичная проверка для пользователя
        assertThat(updatedUser.getComments()).isEmpty();
    }
}