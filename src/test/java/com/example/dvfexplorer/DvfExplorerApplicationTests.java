package com.example.dvfexplorer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class DvfExplorerApplicationTests {

    @Test
    void contextLoads() {
        // Vérifie que l'application se charge sans erreur
    }
}
