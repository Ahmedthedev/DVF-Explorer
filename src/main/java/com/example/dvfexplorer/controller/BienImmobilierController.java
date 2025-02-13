package com.example.dvfexplorer.controller;

import com.example.dvfexplorer.model.BienImmobilier;
import com.example.dvfexplorer.service.BienImmobilierService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public BienImmobilier createBien(@RequestBody BienImmobilier bienImmobilier) {
        return service.save(bienImmobilier);
    }
}
