package com.example.zoo.controller;

import com.example.zoo.model.Species;
import com.example.zoo.service.SpeciesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zoo/species")
public class SpeciesController {
    private final SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<List<Species>> getSpecies() {
        List<Species> species = speciesService.getSpecies();
        return ResponseEntity.ok(species);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Species> getSpeciesById(@PathVariable @Min(0) Integer id) {
        return ResponseEntity.ok(speciesService.getSpeciesById(id));
    }

    @PostMapping
    public ResponseEntity<Species> createSpecies(@RequestBody @Valid Species species) {
        Species createdSpecies = speciesService.createSpecies(species);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpecies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> updateSpecies(
            @PathVariable @Min(0) Integer id,
            @RequestBody @Valid Species species
    ) {
        Species updatedSpecies = speciesService.updateSpecies(id, species);
        return ResponseEntity.ok(updatedSpecies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable @Min(0) Integer id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.noContent().build();
    }
}