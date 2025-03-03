package com.example.zoo.controller;

import com.example.zoo.model.AnimalFood;
import com.example.zoo.service.AnimalFoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zoo/animal-food")
public class AnimalFoodController {
    private final AnimalFoodService animalFoodService;

    @GetMapping
    public ResponseEntity<List<AnimalFood>> getAnimalFoods() {
        List<AnimalFood> animalFoods = animalFoodService.getAnimalFoods();
        return ResponseEntity.ok(animalFoods);
    }

    @PostMapping
    public ResponseEntity<AnimalFood> createAnimalFood(@RequestBody @Valid AnimalFood animalFood) {
        AnimalFood createdAnimalFood = animalFoodService.createAnimalFood(animalFood);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimalFood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimalFood(@PathVariable Integer id) {
        animalFoodService.deleteAnimalFood(id);
        return ResponseEntity.noContent().build();
    }
}