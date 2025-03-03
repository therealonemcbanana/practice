package com.example.zoo.controller;

import com.example.zoo.model.Animal;
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
    public ResponseEntity<List<Animal>> getAnimals() {
        List<Animal> animals = animalService.getAnimals();
        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody @Valid Animal animal) {
        Animal createdAnimal = animalService.createAnimal(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(
            @PathVariable @Min(0) Integer id,
            @RequestBody @Valid Animal animal
    ) {
        Animal updatedAnimal = animalService.updateAnimal(id, animal);
        return ResponseEntity.ok(updatedAnimal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable @Min(0) Integer id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
}