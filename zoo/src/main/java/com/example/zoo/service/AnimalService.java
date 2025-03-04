package com.example.zoo.service;

import com.example.zoo.dto.AnimalDTO;
import com.example.zoo.mapper.AnimalMapper;
import com.example.zoo.model.Animal;
import com.example.zoo.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public List<AnimalDTO> getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        return animals.stream()
                .map(animalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        Animal animal = animalMapper.toEntity(animalDTO);
        Animal savedAnimal = animalRepository.save(animal);
        return animalMapper.toDTO(savedAnimal);
    }

    public AnimalDTO updateAnimal(Integer id, AnimalDTO animalDTO) {
        Animal animal = animalMapper.toEntity(animalDTO);
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));

        existingAnimal.setName(animal.getName());
        existingAnimal.setGender(animal.getGender());
        existingAnimal.setAge(animal.getAge());
        existingAnimal.setAviary(animal.getAviary());
        existingAnimal.setSpecies(animal.getSpecies());
        existingAnimal.setFoodSet(animal.getFoodSet());
        existingAnimal.setEmployees(animal.getEmployees());

        Animal savedAnimal = animalRepository.save(existingAnimal);
        return animalMapper.toDTO(savedAnimal);
    }

    public void deleteAnimal(Integer id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));
        animalRepository.delete(animal);
    }
}