package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.pet.PetDTO;
import com.ensep.petshelter.dto.pet.PetDetailsDTO;
import com.ensep.petshelter.entities.Comment;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.repositories.CommentRepository;
import com.ensep.petshelter.repositories.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;
    private final CommentRepository commentRepository;

    public PetService(PetRepository petRepository, PetDtoMapper petDtoMapper, CommentRepository commentRepository) {
        this.petRepository = petRepository;
        this.petDtoMapper = petDtoMapper;
        this.commentRepository = commentRepository;
    }

    public List<PetDTO> findAllPets(){
        List<Pet> pets = petRepository.findAll();
        return petDtoMapper.toPetDtoList(pets);
    }

    public PetDetailsDTO findById(Long id){
        Pet pet = petRepository.findById(id).orElse(null);
        return petDtoMapper.toPetDetailsDTO(pet);
    }

    public ResponseEntity<?> deleteCommentById(Long petId, Long commentId){
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
