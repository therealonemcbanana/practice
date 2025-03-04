package com.example.zoo.mapper;

import com.example.zoo.dto.FoodDTO;
import com.example.zoo.model.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDTO toDTO(Food food);
    Food toEntity(FoodDTO foodDTO);
}
