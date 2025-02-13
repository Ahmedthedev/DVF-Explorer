package com.example.dvfexplorer.service;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.repository.BienImmobilierRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienImmobilierService {

    private final BienImmobilierRepository repository;

    public BienImmobilierService(BienImmobilierRepository repository) {
        this.repository = repository;
    }

    public List<BienImmobilier> getAll() {
        return repository.findAll();
    }

    public List<BienImmobilier> getByCommune(String commune) {
        return repository.findByCommune(commune);
    }

    public BienImmobilier save(BienImmobilier bienImmobilier) {
        return repository.save(bienImmobilier);
    }
}

