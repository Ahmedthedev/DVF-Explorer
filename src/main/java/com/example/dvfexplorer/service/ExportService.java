package com.example.dvfexplorer.service;
import org.springframework.batch.core.Job;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher; 
import org.springframework.stereotype.Service;


@Service
public class ExportService {

    private final JobLauncher jobLauncher;
    private final Job exportCsvJob;

    public ExportService(JobLauncher jobLauncher, Job exportCsvJob) {
        this.jobLauncher = jobLauncher;
        this.exportCsvJob = exportCsvJob;
    }

    public void exportCsv() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(exportCsvJob, params);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'exécution du job d'export", e);
        }
    }
}