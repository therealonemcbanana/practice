package com.example.zoo.repository;

import com.example.zoo.model.AnimalEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalEmployeeRepository extends JpaRepository<AnimalEmployee, Integer> {
}