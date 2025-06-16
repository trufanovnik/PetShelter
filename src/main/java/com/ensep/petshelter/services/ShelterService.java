package com.ensep.petshelter.services;

import com.ensep.petshelter.dto.ShelterDto;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.mapper.ShelterDtoMapper;
import com.ensep.petshelter.repositories.ShelterRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterDtoMapper shelterDtoMapper;

    public ShelterService(ShelterRepository shelterRepository, ShelterDtoMapper shelterDtoMapper) {
        this.shelterRepository = shelterRepository;
        this.shelterDtoMapper = shelterDtoMapper;
    }

    public List<ShelterDto> findAllShelters(){
        List<Shelter> shelters = shelterRepository.findAll();
        return shelterDtoMapper.toShelterDtoList(shelters);
    }

    public ShelterDto findById(Long id){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        return shelterDtoMapper.toShelterDto(shelter);
    }

    public Shelter createShelter(Shelter shelter){
        return shelterRepository.save(shelter);
    }

    public void removeShelter(Long id){
        shelterRepository.deleteById(id);
    }

    public ShelterDto updateShelter(Long id, Map<String, Object> updates){
        Shelter shelter = shelterRepository.findById(id).orElse(null);
        updates.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Shelter.class, fieldName);
            if (field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, shelter, fieldValue);
            }
        });
        return shelterDtoMapper.toShelterDto(shelterRepository.save(shelter));
    }
}
