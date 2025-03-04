package com.example.zoo.mapper;

import  com.example.zoo.model.Animal;
import com.example.zoo.dto.AnimalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FoodMapper.class, EmployeeMapper.class})
public interface AnimalMapper {
    AnimalDTO toDTO(Animal animal);
    Animal toEntity(AnimalDTO animalDTO);
}
