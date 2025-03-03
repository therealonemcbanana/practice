package com.example.zoo.service;

import com.example.zoo.model.AnimalEmployee;
import com.example.zoo.repository.AnimalEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalEmployeeService {
    private final AnimalEmployeeRepository animalEmployeeRepository;

    public List<AnimalEmployee> getAnimalEmployees() {
        return animalEmployeeRepository.findAll();
    }

    public AnimalEmployee createAnimalEmployee(AnimalEmployee animalEmployee) {
        return animalEmployeeRepository.save(animalEmployee);
    }

    public void deleteAnimalEmployee(Integer id) {
        AnimalEmployee animalEmployee = animalEmployeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal-employee relationship not found with id: " + id));
        animalEmployeeRepository.delete(animalEmployee);
    }
}