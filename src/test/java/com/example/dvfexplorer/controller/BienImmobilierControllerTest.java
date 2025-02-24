package com.example.dvfexplorer.controller;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.service.BienImmobilierService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BienImmobilierControllerTest {

    @InjectMocks
    private BienImmobilierController bienImmobilierController;

    @Mock
    private BienImmobilierService bienImmobilierService;

    private BienImmobilier bien1, bien2;

    private List<BienImmobilier> biens;
    
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

        biens = Arrays.asList(bien1,bien2);
    }

    @Test
    public void shouldGetAllBiens() {
        List<BienImmobilier> biens = Arrays.asList(bien1, bien2);

       
        given(bienImmobilierService.getAll()).willReturn(biens);

       
        List<BienImmobilier> result = bienImmobilierController.getAllBiens();

        
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getCommune()).isEqualTo("Paris");
        assertThat(result.get(1).getCommune()).isEqualTo("Lyon");
    }

    @Test
    public void shouldGetBiensByCommune() {
        given(bienImmobilierService.getByCommune("Paris")).willReturn(List.of(bien1));

        List<BienImmobilier> result = bienImmobilierController.getByCommune("Paris");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCommune()).isEqualTo("Paris");
    }

    @Test
    public void shouldGetBiensAvenir() {
        given(bienImmobilierService.getBiensAVenir()).willReturn(biens);

        List<BienImmobilier> result = bienImmobilierController.getBiensAvenir();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getCommune()).isEqualTo("Paris");
        assertThat(result.get(1).getCommune()).isEqualTo("Lyon");
    }
}
