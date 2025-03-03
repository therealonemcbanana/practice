package com.example.zoo.service;

import com.example.zoo.model.Animal;
import com.example.zoo.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Integer id, Animal animal) {
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));

        existingAnimal.setName(animal.getName());
        existingAnimal.setGender(animal.getGender());
        existingAnimal.setAge(animal.getAge());
        existingAnimal.setAviary(animal.getAviary());
        existingAnimal.setSpecies(animal.getSpecies());

        return animalRepository.save(existingAnimal);
    }

    public void deleteAnimal(Integer id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));
        animalRepository.delete(animal);
    }
}