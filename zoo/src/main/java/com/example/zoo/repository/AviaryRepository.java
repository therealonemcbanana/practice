package com.example.zoo.repository;

import com.example.zoo.model.Aviary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AviaryRepository extends JpaRepository<Aviary, Integer> {
}