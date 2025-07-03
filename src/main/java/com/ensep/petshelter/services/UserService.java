package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.user.UserEntityDTO;
import com.ensep.petshelter.entities.UserEntity;
import com.ensep.petshelter.mapper.UserEntityDtoMapper;
import com.ensep.petshelter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEntityDtoMapper userEntityDtoMapper;

    @Transactional(readOnly = true)
    public UserEntityDTO findById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userEntityDtoMapper.userEntityToUserEntityDto(userEntity);
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public void removeUser(Long id){
        userRepository.deleteById(id);
    }
}
