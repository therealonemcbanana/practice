package com.example.zoo.service;

import com.example.zoo.model.Food;
import com.example.zoo.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> getFood() {
        return foodRepository.findAll();
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public Food updateFood(Integer id, Food food) {
        Food existingFood = foodRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + id));

        existingFood.setName(food.getName());
        existingFood.setAmount(food.getAmount());

        return foodRepository.save(existingFood);
    }

    public void deleteFood(Integer id) {
        Food food = foodRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + id));
        foodRepository.delete(food);
    }
}
