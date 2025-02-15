package com.example.dvfexplorer.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.repository.BienImmobilierRepository;

import org.springframework.data.domain.Sort;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private BienImmobilierRepository bienRepository;


    @Bean
    public RepositoryItemReader<BienImmobilier> itemReader() {
        RepositoryItemReader<BienImmobilier> reader = new RepositoryItemReader<>();
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("saleDateUnix", Sort.Direction.DESC);
        reader.setSort(sort);
        reader.setRepository(bienRepository);
        reader.setMethodName("findAll");
        return reader;
    }

    @Bean
    public FlatFileItemWriter<BienImmobilier> itemWriter() {
        System.out.println("Export CSV en cours...");
        return new FlatFileItemWriterBuilder<BienImmobilier>()
            .name("bienItemWriter")
            .resource(new FileSystemResource("export_biens.csv"))
            .delimited()
            .delimiter(";")
            .names("id", "type", "surface", "commune", "saleDateUnix")
            .build();
    }

    @Bean
    public Step exportStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("exportStep", jobRepository)
                .<BienImmobilier, BienImmobilier>chunk(10, transactionManager)
                .reader(itemReader())
                .processor(new PassThroughItemProcessor<>()) // Pas de transformation
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job exportCsvJob(JobRepository jobRepository, Step exportStep) {
        return new JobBuilder("exportCsvJob", jobRepository)
                .start(exportStep)
                .build();
    }
}

