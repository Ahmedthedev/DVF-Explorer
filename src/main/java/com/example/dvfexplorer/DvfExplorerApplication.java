package com.example.dvfexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.dvfexplorer.model")
@ComponentScan(basePackages = "com.example.dvfexplorer")
@EnableJpaRepositories(basePackages = "com.example.dvfexplorer.repository")
public class DvfExplorerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DvfExplorerApplication.class, args);
    }
}
