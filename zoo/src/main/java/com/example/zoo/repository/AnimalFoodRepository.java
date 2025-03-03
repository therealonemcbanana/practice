package com.example.zoo.repository;

import com.example.zoo.model.AnimalFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalFoodRepository extends JpaRepository<AnimalFood, Integer> {
}