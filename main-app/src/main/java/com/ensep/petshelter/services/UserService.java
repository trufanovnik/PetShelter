package com.ensep.petshelter.services;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.dto.user.UserLikedPetsDto;
import com.ensep.petshelter.dto.user.UserUpdateDTO;
import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.mapper.UserLikedPetsMapper;
import com.ensep.petshelter.repositories.AccountRepository;
import com.ensep.petshelter.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final UserLikedPetsMapper mapper;

    @Transactional(readOnly = true)
    public UserLikedPetsDto getUserLikedPets(Long id){
        UserEntity user = userRepository.findWithLikedPetsById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return mapper.toDto(user);
    }

    @Transactional
    public UserLikedPetsDto updateUser(Long id, UserUpdateDTO dto, CustomUserDetails userDetails) {
        String login = userDetails.getAccount().getLogin();

        UserEntity user = userRepository.findByIdOrThrow(id);
        Account account = accountRepository.findByLoginOrThrow(login);

        user.setUsername(dto.getUsername());
        account.setLogin(dto.getUsername());

        return mapper.toDto(userRepository.save(user));
    }
}
