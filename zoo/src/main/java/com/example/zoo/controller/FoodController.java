package com.example.zoo.controller;

import com.example.zoo.model.Food;
import com.example.zoo.service.FoodService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zoo/food")
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<Food>> getFood() {
        List<Food> food = foodService.getFood();
        return ResponseEntity.ok(food);
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody @Valid Food food) {
        Food createdFood = foodService.createFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood (@PathVariable @Min(0) Integer id, @RequestBody @Valid Food food) {
        Food updatedFood = foodService.updateFood(id, food);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood (@PathVariable @Min(0) Integer id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
