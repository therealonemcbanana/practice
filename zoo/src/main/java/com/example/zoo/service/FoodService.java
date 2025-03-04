package com.example.zoo.service;

import com.example.zoo.dto.FoodDTO;
import com.example.zoo.mapper.FoodMapper;
import com.example.zoo.model.Food;
import com.example.zoo.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public List<FoodDTO> getFood() {
        List<Food> foodList = foodRepository.findAll();
        return foodList.stream()
                .map(foodMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FoodDTO createFood(FoodDTO foodDTO) {
        Food food = foodMapper.toEntity(foodDTO);
        Food savedFood = foodRepository.save(food);
        return foodMapper.toDTO(savedFood);
    }

    public FoodDTO updateFood(Integer id, FoodDTO foodDTO) {
        Food existingFood = foodRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + id));

        Food food = foodMapper.toEntity(foodDTO);
        existingFood.setName(food.getName());
        existingFood.setAmount(food.getAmount());
        existingFood.setAnimals(food.getAnimals());

        Food savedFood = foodRepository.save(food);
        return foodMapper.toDTO(savedFood);
    }

    public void deleteFood(Integer id) {
        Food food = foodRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + id));
        foodRepository.delete(food);
    }
}
