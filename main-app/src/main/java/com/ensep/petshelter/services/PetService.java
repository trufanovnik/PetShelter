package com.ensep.petshelter.services;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.pets.PetDetailsDTO;
import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.Comment;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.mapper.PetDtoMapper;
import com.ensep.petshelter.repositories.CommentRepository;
import com.ensep.petshelter.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetDtoMapper petDtoMapper;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public PetDetailsDTO findById(Long id) {
        Pet pet = petRepository.findByIdOrThrow(id);

        return petDtoMapper.toPetDetailsDTO(pet);
    }

    @Transactional
    public PetDetailsDTO addComment(Long id, String content, CustomUserDetails userDetails) {
        Pet pet = petRepository.findByIdOrThrow(id);
        Account account = userDetails.getAccount();

        Comment comment = new Comment();
        comment.setPet(pet);
        comment.setContent(content);
        comment.setAccount(account);
        commentRepository.save(comment);

        return petDtoMapper.toPetDetailsDTO(pet);
    }

    @Transactional
    public PetDetailsDTO deleteComment(Long petId, Long commentId) {
        Pet pet = petRepository.findByIdOrThrow(petId);
        Comment comment = commentRepository.findByIdOrThrow(commentId);
        pet.getComments().remove(comment);

        return petDtoMapper.toPetDetailsDTO(pet);
    }
}
