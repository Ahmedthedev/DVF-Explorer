package com.example.dvfexplorer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dvfexplorer.model.BienImmobilier;

import java.util.List;

@Repository
public interface BienImmobilierRepository extends JpaRepository<BienImmobilier, Long> {
    List<BienImmobilier> findByCommune(String commune);
}
