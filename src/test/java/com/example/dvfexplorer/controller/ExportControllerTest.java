package com.example.dvfexplorer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
class ExportControllerTest {

    private MockMvc mockMvc;

    private ExportController exportController;

    @TempDir
    Path tempDir;

    private File tempCsvFile;

    @BeforeEach
    void setUp() throws IOException {
        tempCsvFile = tempDir.resolve("export_biens.csv").toFile();
        try (FileWriter writer = new FileWriter(tempCsvFile)) {
            writer.write("id,nom,prix\n1,Appartement,50000\n");
        }

        exportController = new ExportController(tempCsvFile.getAbsolutePath());
        mockMvc = MockMvcBuilders.standaloneSetup(exportController).build();
    }
    
    @Test
    void shouldReturnFileWhenExists() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/export/download/csv"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export_biens.csv"))
                .andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM))
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("id,nom,prix\n1,Appartement,50000\n");
    }

    @Test
    void shouldReturnNotFoundWhenFileDoesNotExist() throws Exception {
        ExportController exportController = new ExportController("invalid_file.csv");
        mockMvc = MockMvcBuilders.standaloneSetup(exportController).build();

        mockMvc.perform(get("/api/export/download/csv"))
                .andExpect(status().isNotFound());
    }

}
