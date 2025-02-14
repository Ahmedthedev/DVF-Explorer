package com.example.dvfexplorer;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class DvfExplorerApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        // Vérifie que l'application se charge sans erreur
    }


    @Test
    void testDatabaseConnection() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("✅ Connexion à la base réussie : " + conn.getMetaData().getURL());
        }
    }
}
