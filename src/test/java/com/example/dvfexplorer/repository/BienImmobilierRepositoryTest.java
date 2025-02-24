package com.example.dvfexplorer.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.dvfexplorer.model.BienImmobilier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.List;
@DataJpaTest
@ActiveProfiles("test")
class BienImmobilierRepositoryTest {

    @Autowired
    private BienImmobilierRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindBiensByCommune() {
        BienImmobilier bien = new BienImmobilier();
        bien.setCommune("Paris");
        entityManager.persist(bien);
        entityManager.flush();

        List<BienImmobilier> result = repository.findByCommune("Paris");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).getCommune());
    }

    @Test
    void shouldFindBiensBySaleDateUnixAfter() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 10000);
        BienImmobilier bien = new BienImmobilier();
        bien.setSaleDateUnix(new Timestamp(System.currentTimeMillis() + 10000));
        entityManager.persist(bien);
        entityManager.flush();

        List<BienImmobilier> result = repository.findBySaleDateUnixAfter(timestamp);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}

