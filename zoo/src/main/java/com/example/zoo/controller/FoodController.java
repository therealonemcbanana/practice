package com.example.zoo.controller;

import com.example.zoo.dto.FoodDTO;
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
    public ResponseEntity<List<FoodDTO>> getFood() {
        List<FoodDTO> foodDTOList = foodService.getFood();
        return ResponseEntity.ok(foodDTOList);
    }

    @PostMapping
    public ResponseEntity<FoodDTO> createFood(@RequestBody @Valid FoodDTO foodDTO) {
        FoodDTO createdFoodDTO = foodService.createFood(foodDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFoodDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFood (@PathVariable @Min(0) Integer id, @RequestBody @Valid FoodDTO foodDTO) {
        FoodDTO updatedFoodDTO = foodService.updateFood(id, foodDTO);
        return ResponseEntity.ok(updatedFoodDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood (@PathVariable @Min(0) Integer id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
