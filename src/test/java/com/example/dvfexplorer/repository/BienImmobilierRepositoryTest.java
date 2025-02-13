package com.example.dvfexplorer.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.dvfexplorer.model.BienImmobilier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BienImmobilierRepositoryTest {

    @Autowired
    private BienImmobilierRepository repository;

    @Test
    void testSaveBien() {
        BienImmobilier bien = new BienImmobilier();
        bien.setCommune("Paris");
        repository.save(bien);

        List<BienImmobilier> biens = repository.findByCommune("Paris");
        assertFalse(biens.isEmpty());
    }
}
