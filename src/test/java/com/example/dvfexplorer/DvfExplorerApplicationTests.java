package com.example.dvfexplorer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.swagger.v3.oas.annotations.tags.Tag;


@ActiveProfiles("test")
@SpringBootTest
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