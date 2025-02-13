package com.example.dvfexplorer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.repository.BienImmobilierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class BienImmobilierServiceTest {

    @Mock
    private BienImmobilierRepository repository;

    @InjectMocks
    private BienImmobilierService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBiens() {
        when(repository.findAll()).thenReturn(List.of(new BienImmobilier(), new BienImmobilier()));
        assertEquals(2, service.getAll().size());
    }

    @Test
    void testGetBienByCommune() {
        when(repository.findByCommune("Paris")).thenReturn(List.of(new BienImmobilier()));
        assertEquals(1, service.getByCommune("Paris").size());
    }

    @Test
    void testSaveBien() {
        BienImmobilier bien = new BienImmobilier();
        when(repository.save(any(BienImmobilier.class))).thenReturn(bien);
        assertNotNull(service.save(bien));
    }
}
