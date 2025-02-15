package com.example.dvfexplorer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.dvfexplorer.service.ExportService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ExportController.class)
class ExportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExportService exportService;

    @Test
    void shouldTriggerCsvExport() throws Exception {
        doNothing().when(exportService).exportCsv();

        mockMvc.perform(get("/api/export/download/csv"))
                .andExpect(status().isOk())
                .andExpect(content().string("Export CSV lancé avec succès!"));

        verify(exportService, times(1)).exportCsv();
    }

    @Test
    void shouldReturnErrorMessageOnFailure() throws Exception {
        doThrow(new RuntimeException("Job failed")).when(exportService).exportCsv();

        mockMvc.perform(get("/api/export/download/csv"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erreur lors de l'export: Job failed"));

        verify(exportService, times(1)).exportCsv();
    }
}
