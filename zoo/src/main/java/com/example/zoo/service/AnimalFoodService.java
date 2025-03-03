package com.example.zoo.service;

import com.example.zoo.model.AnimalFood;
import com.example.zoo.repository.AnimalFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalFoodService {
    private final AnimalFoodRepository animalFoodRepository;

    public List<AnimalFood> getAnimalFoods() {
        return animalFoodRepository.findAll();
    }

    public AnimalFood createAnimalFood(AnimalFood animalFood) {
        return animalFoodRepository.save(animalFood);
    }

    public void deleteAnimalFood(Integer id) {
        AnimalFood animalFood = animalFoodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal-food relationship not found with id: " + id));
        animalFoodRepository.delete(animalFood);
    }
}