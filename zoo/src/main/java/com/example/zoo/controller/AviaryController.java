package com.example.zoo.controller;

import com.example.zoo.model.Aviary;
import com.example.zoo.service.AviaryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zoo/aviary")
public class AviaryController {
    private final AviaryService aviaryService;

    @GetMapping
    public ResponseEntity<List<Aviary>> getAviaries() {
        List<Aviary> aviaries = aviaryService.getAviaries();
        return ResponseEntity.ok(aviaries);
    }

    @PostMapping
    public ResponseEntity<Aviary> createAviary(@RequestBody @Valid Aviary aviary) {
        Aviary createdAviary = aviaryService.createAviary(aviary);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAviary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aviary> updateAviary(
            @PathVariable @Min(0) Integer id,
            @RequestBody @Valid Aviary aviary
    ) {
        Aviary updatedAviary = aviaryService.updateAviary(id, aviary);
        return ResponseEntity.ok(updatedAviary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAviary(@PathVariable @Min(0) Integer id) {
        aviaryService.deleteAviary(id);
        return ResponseEntity.noContent().build();
    }
}