package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.user.UserLikedPetsDto;
import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.mapper.UserLikedPetsMapper;
import com.ensep.petshelter.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserLikedPetsMapper mapper;

    @Transactional(readOnly = true)
    public UserLikedPetsDto getUserLikedPets(Long id){
        UserEntity user = userRepository.findWithLikedPetsById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return mapper.toDto(user);
    }
}
