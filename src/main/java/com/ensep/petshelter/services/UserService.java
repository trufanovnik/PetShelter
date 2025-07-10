package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.user.UserEntityDTO;
import com.ensep.petshelter.dto.user.UserEntityUpdateDTO;
import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.mapper.UserEntityDtoMapper;
import com.ensep.petshelter.mapper.UserEntityUpdateMapper;
import com.ensep.petshelter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEntityDtoMapper userEntityDtoMapper;
    private final UserEntityUpdateMapper userEntityUpdateMapper;

    @Transactional(readOnly = true)
    public UserEntityDTO findById(Long id){
        UserEntity userEntity = userRepository.findByIdOrThrow(id);
        return userEntityDtoMapper.userEntityToUserEntityDto(userEntity);
    }

    @Transactional
    public UserEntityDTO updateUser(Long id, UserEntityUpdateDTO userUpdate){
        UserEntity user = userRepository.findByIdOrThrow(id);

        userEntityUpdateMapper.updateUserFromDto(userUpdate, user);
        return userEntityDtoMapper.userEntityToUserEntityDto(userRepository.save(user));
    }

    @Transactional
    public void removeUser(Long id){
        userRepository.deleteById(id);
    }
}
