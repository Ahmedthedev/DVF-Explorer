package com.example.dvfexplorer.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.service.BienImmobilierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

public class BienImmobilierControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BienImmobilierService service;

    @InjectMocks
    private BienImmobilierController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAllBiens() throws Exception {
        when(service.getAll()).thenReturn(List.of(new BienImmobilier(), new BienImmobilier()));

        mockMvc.perform(get("/api/biens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testCreateBien() throws Exception {
        BienImmobilier bien = new BienImmobilier();
        when(service.save(any(BienImmobilier.class))).thenReturn(bien);

        mockMvc.perform(post("/api/biens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bien)))
                .andExpect(status().isOk());
    }
}
