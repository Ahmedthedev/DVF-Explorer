package com.example.dvfexplorer.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.dvfexplorer.model.BienImmobilier;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BienImmobilierRepository extends JpaRepository<BienImmobilier, Long>, JpaSpecificationExecutor<BienImmobilier> {
    List<BienImmobilier> findByCommune(String commune);
    List<BienImmobilier> findBySaleDateUnixAfter(Timestamp timestamp);
}
