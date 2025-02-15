package com.example.dvfexplorer.controller;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.service.BienImmobilierService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/biens")
public class BienImmobilierController {

    private final BienImmobilierService service;

    public BienImmobilierController(BienImmobilierService service) {
        this.service = service;
    }

    @GetMapping
    public List<BienImmobilier> getAllBiens() {
        return service.getAll();
    }

    @GetMapping("/{commune}")
    public List<BienImmobilier> getByCommune(@PathVariable String commune) {
        return service.getByCommune(commune);
    }

    @GetMapping("/avenir")
    public List<BienImmobilier> getBiensAvenir() {
        return service.getBiensAVenir();
    } 
    
    @GetMapping("/avenirFilter")
    public ResponseEntity<Page<BienImmobilier>> getBiensAvenirFilter(
            @RequestParam(required = false) String minSaleDate,
            @RequestParam(required = false) Double minPrix,
            @RequestParam(required = false) Double maxPrix,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "saleDateUnix,asc") String sort) {

        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

        Timestamp timestamp = (minSaleDate == null || minSaleDate.equalsIgnoreCase("null"))
        ? new Timestamp(System.currentTimeMillis())
        : Timestamp.valueOf(minSaleDate.replace("T", " ").replace("Z", "")); 

        Page<BienImmobilier> biens = service.getBiensAvenirFilter(timestamp, minPrix, maxPrix, pageable);
        return ResponseEntity.ok(biens);
    }

    @PostMapping
    public BienImmobilier createBien(@RequestBody BienImmobilier bienImmobilier) {
        return service.save(bienImmobilier);
    }
}
