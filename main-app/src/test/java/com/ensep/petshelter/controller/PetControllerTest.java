package com.ensep.petshelter.controller;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.controllers.rest.PetController;
import com.ensep.petshelter.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    @WithMockUser
    void doLike_WhenAuth_ShouldReturnOk() throws Exception {
        Long petId = 1L;

        mockMvc.perform(post("/api/pet/{id}", petId))
                .andExpect(status().isOk());

        verify(petService, times(1)).doLike(eq(petId), any(CustomUserDetails.class));
    }
}
