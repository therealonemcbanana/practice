package com.example.zoo.controller;

import com.example.zoo.model.AnimalEmployee;
import com.example.zoo.service.AnimalEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zoo/animal-employee")
public class AnimalEmployeeController {
    private final AnimalEmployeeService animalEmployeeService;

    @GetMapping
    public ResponseEntity<List<AnimalEmployee>> getAnimalEmployees() {
        List<AnimalEmployee> animalEmployees = animalEmployeeService.getAnimalEmployees();
        return ResponseEntity.ok(animalEmployees);
    }

    @PostMapping
    public ResponseEntity<AnimalEmployee> createAnimalEmployee(@RequestBody @Valid AnimalEmployee animalEmployee) {
        AnimalEmployee createdAnimalEmployee = animalEmployeeService.createAnimalEmployee(animalEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimalEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimalEmployee(@PathVariable Integer id) {
        animalEmployeeService.deleteAnimalEmployee(id);
        return ResponseEntity.noContent().build();
    }
}