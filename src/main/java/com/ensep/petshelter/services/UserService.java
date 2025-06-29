package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.user.UserEntityDTO;
import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.mapper.UserEntityDtoMapper;
import com.ensep.petshelter.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

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

    public UserEntityDTO updateUser(Long id, Map<String, Object> updates){
        UserEntity user = userRepository.findById(id).orElse(null);
        updates.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(UserEntity.class, fieldName);
            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, user, fieldValue);
            }
        });
        return userEntityDtoMapper.userEntityToUserEntityDto(userRepository.save(user));
    }

    public void removeUser(Long id){
        userRepository.deleteById(id);
    }
}
