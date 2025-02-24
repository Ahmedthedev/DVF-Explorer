package com.example.dvfexplorer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class DatabaseConnectionTest {

    @Test
    void testDatabaseConnection() {
        String url = "jdbc:postgresql://localhost:5432/ahmedhachemi";
        String user = "ahmedhachemi";
        String password = "ahmedhachemi";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to: " + conn.getMetaData().getURL());
            System.out.println("Database Product: " + conn.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
