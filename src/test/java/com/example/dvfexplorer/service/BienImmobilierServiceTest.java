package com.example.dvfexplorer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.repository.BienImmobilierRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.List;


@ExtendWith(MockitoExtension.class) // Active Mockito pour les tests unitaires
public class BienImmobilierServiceTest {

    @Mock
    private BienImmobilierRepository repository;

    @InjectMocks
    private BienImmobilierService service;

    private BienImmobilier bien1, bien2;
    
    @BeforeEach
    void setUp() {
        bien1 = new BienImmobilier();
        bien1.setId(1L);
        bien1.setCommune("Paris");
        bien1.setStartingPrice(50000.0);
        bien1.setSaleDateUnix(new Timestamp(System.currentTimeMillis() + 1000000));

        bien2 = new BienImmobilier();
        bien2.setId(2L);
        bien2.setCommune("Lyon");
        bien2.setStartingPrice(70000.0);
        bien2.setSaleDateUnix(new Timestamp(System.currentTimeMillis() + 2000000));
    }


    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(bien1, bien2));
    
        List<BienImmobilier> result = service.getAll(); 
    
        assertEquals(2, result.size()); 
        verify(repository, times(1)).findAll(); 
    }


    @Test
    void testGetByCommune() {
        when(repository.findByCommune("Paris")).thenReturn(List.of(bien1));

        List<BienImmobilier> result = service.getByCommune("Paris");

        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).getCommune());
        verify(repository, times(1)).findByCommune("Paris");
    }


    @Test
    void testSave() {
        when(repository.save(bien1)).thenReturn(bien1);

        BienImmobilier result = service.save(bien1);

        assertNotNull(result);
        assertEquals(bien1.getId(), result.getId());
        verify(repository, times(1)).save(bien1);
    }

    
    @Test
    void testGetBiensAVenir() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        when(repository.findBySaleDateUnixAfter(now)).thenReturn(List.of(bien1, bien2));

        List<BienImmobilier> result = service.getBiensAVenir();

        assertEquals(2, result.size());
        verify(repository, times(1)).findBySaleDateUnixAfter(now);
    }

    
    @Test
    void testGetBiensAvenirFilter() {
        // Création des critères de filtrage
        Timestamp minDate = new Timestamp(System.currentTimeMillis() - 10000); 
        Double minPrix = 40000.0;
        Double maxPrix = 80000.0;
        PageRequest pageable = PageRequest.of(0, 10);

        // Création d'une fausse page de résultats contenant deux biens immobiliers
        Page<BienImmobilier> page = new PageImpl<>(List.of(bien1, bien2));

        // Simulation du comportement du repository : quand `findAll()` est appelé avec une spécification quelconque et la pagination donnée,
        // il doit retourner notre fausse page contenant `bien1` et `bien2`
        when(repository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

        // Exécution de la méthode de service avec les mêmes critères
        Page<BienImmobilier> result = service.getBiensAvenirFilter(minDate, minPrix, maxPrix, pageable);

        // Vérification que le résultat contient bien 2 éléments comme prévu
        assertEquals(2, result.getTotalElements());

        // Vérification que `findAll` a bien été appelé une seule fois avec une Specification et le Pageable donné
        verify(repository, times(1)).findAll(any(Specification.class), eq(pageable));
    }
}
