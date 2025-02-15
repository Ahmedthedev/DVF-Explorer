package com.example.dvfexplorer.service;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.repository.BienImmobilierRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public List<BienImmobilier> getBiensAVenir() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return repository.findBySaleDateUnixAfter(now);
    }

    public Page<BienImmobilier> getBiensAvenirFilter(Timestamp minSaleDate, Double minPrix, Double maxPrix, Pageable pageable) {
        Specification<BienImmobilier> spec = Specification.where((root, query, cb) -> cb.greaterThan(root.get("saleDateUnix"), minSaleDate));

        if (minPrix != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("startingPrice"), minPrix));
        }
        if (maxPrix != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("startingPrice"), maxPrix));
        }

        return repository.findAll(spec, pageable);
    }
}

    


