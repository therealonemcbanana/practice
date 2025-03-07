package com.example.zoo.service;

import com.example.zoo.model.Aviary;
import com.example.zoo.repository.AviaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AviaryService {
    private final AviaryRepository aviaryRepository;

    public List<Aviary> getAviaries() {
        return aviaryRepository.findAll();
    }

    public Aviary getAviaryById(Integer id) {
        return aviaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id: " + id));
    }

    public Aviary createAviary(Aviary aviary) {
        return aviaryRepository.save(aviary);
    }

    public Aviary updateAviary(Integer id, Aviary aviary) {
        Aviary existingAviary = aviaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aviary not found with id: " + id));

        existingAviary.setSize(aviary.getSize());
        existingAviary.setState(aviary.getState());

        return aviaryRepository.save(existingAviary);
    }

    public void deleteAviary(Integer id) {
        Aviary aviary = aviaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aviary not found with id: " + id));
        aviaryRepository.delete(aviary);
    }
}