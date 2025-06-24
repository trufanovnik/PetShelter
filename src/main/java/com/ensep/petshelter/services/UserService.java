package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.UserEntityDTO;
import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.mapper.UserEntityDtoMapper;
import com.ensep.petshelter.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserEntityDtoMapper userEntityDtoMapper;

    public UserService(UserRepository userRepository, UserEntityDtoMapper userEntityDtoMapper) {
        this.userRepository = userRepository;
        this.userEntityDtoMapper = userEntityDtoMapper;
    }

    public UserEntityDTO findById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userEntityDtoMapper.userEntityToUserEntityDto(userEntity);
    }
}
