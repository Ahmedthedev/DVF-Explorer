package com.example.dvfexplorer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExportServiceTest {

    private JobLauncher jobLauncher;
    private Job exportCsvJob;
    private ExportService exportService;

    @BeforeEach
    void setUp() {
        jobLauncher = mock(JobLauncher.class); 
        exportCsvJob = mock(Job.class);
        exportService = new ExportService(jobLauncher, exportCsvJob); 
    }

    @Test
    void shouldRunExportCsvJobSuccessfully() throws Exception {
        JobExecution jobExecution = mock(JobExecution.class);
        
        when(jobLauncher.run(any(Job.class), any(JobParameters.class))).thenReturn(jobExecution);

        assertDoesNotThrow(() -> exportService.exportCsv()); 
        verify(jobLauncher, times(1)).run(any(Job.class), any(JobParameters.class)); 
    }

    @Test
    void shouldThrowExceptionWhenJobFails() throws Exception {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class))).thenThrow(new RuntimeException("Job failed")); 

        Exception exception = assertThrows(RuntimeException.class, () -> exportService.exportCsv()); 
        assertEquals("Erreur lors de l'exécution du job d'export", exception.getMessage());
    }
}
