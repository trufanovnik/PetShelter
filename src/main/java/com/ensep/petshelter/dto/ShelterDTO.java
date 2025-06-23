package com.ensep.petshelter.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShelterDTO {
    private Long id;
    private String title;
    private String city;
    private List<PetDTO> pets;
}
