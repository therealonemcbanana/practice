package com.example.zoo.service;

import com.example.zoo.model.Species;
import com.example.zoo.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesService {
    private final SpeciesRepository speciesRepository;

    public List<Species> getSpecies() {
        return speciesRepository.findAll();
    }

    public Species getSpeciesById(Integer id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));
    }

    public Species createSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public Species updateSpecies(Integer id, Species species) {
        Species existingSpecies = speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Species not found with id: " + id));

        existingSpecies.setTitle(species.getTitle());
        existingSpecies.setDescription(species.getDescription());

        return speciesRepository.save(existingSpecies);
    }

    public void deleteSpecies(Integer id) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Species not found with id: " + id));
        speciesRepository.delete(species);
    }
}