package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.pet.PetDetailsDTO;
import com.ensep.petshelter.entities.Comment;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.repositories.CommentRepository;
import com.ensep.petshelter.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<PetDTO> findAllPets(){
        List<Pet> pets = petRepository.findAll();
        return petDtoMapper.toPetDtoList(pets);
    }

    @Transactional(readOnly = true)
    public PetDetailsDTO findById(Long id){
        Pet pet = petRepository.findById(id).orElse(null);
        return petDtoMapper.toPetDetailsDTO(pet);
    }

    @Transactional
    public PetDetailsDTO addComment(Long id, Comment comment){
        Pet pet = petRepository.findById(id).orElse(null);
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setPet(pet);
        newComment.setAuthor(comment.getAuthor());
        newComment.setShelter(comment.getShelter());
        newComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(newComment);
        return petDtoMapper.toPetDetailsDTO(pet);
    }

    @Transactional
    public ResponseEntity<String> deleteCommentById(Long petId, Long commentId){
        Pet pet = petRepository.findById(petId).orElse(null);
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (!pet.getComments().contains(comment)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Данный комментарий не принадлежит этому питомцу.");
        }
        pet.getComments().remove(comment);
        return ResponseEntity.noContent().build();
    }
}
