package com.example.zoo.controller;

import com.example.zoo.dto.AnimalDTO;
import com.example.zoo.service.AnimalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zoo/animal")
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAnimals() {
        List<AnimalDTO> animalsDTO = animalService.getAnimals();
        return ResponseEntity.ok(animalsDTO);
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> createAnimal(@RequestBody @Valid AnimalDTO animalDTO) {
        AnimalDTO createdAnimalDTO = animalService.createAnimal(animalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimalDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDTO> updateAnimal(
            @PathVariable @Min(0) Integer id,
            @RequestBody @Valid AnimalDTO animalDTO
    ) {
        AnimalDTO updatedAnimalDTO = animalService.updateAnimal(id, animalDTO);
        return ResponseEntity.ok(updatedAnimalDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable @Min(0) Integer id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}