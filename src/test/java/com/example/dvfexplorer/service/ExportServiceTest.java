package com.example.dvfexplorer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ExportServiceTest {

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job exportCsvJob;

    @InjectMocks
    private ExportService exportService;

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

