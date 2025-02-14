package com.example.dvfexplorer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import io.swagger.v3.oas.annotations.tags.Tag;

import javax.sql.DataSource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class DvfExplorerApplicationTests {

    @Test
    @Tag(name = "skip-ci")
    void contextLoads() {
    }

    @Test
    @Tag(name = "skip-ci")
    void testDatabaseConnection() {
    }

}
