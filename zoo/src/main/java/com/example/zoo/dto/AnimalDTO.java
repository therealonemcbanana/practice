package com.example.zoo.dto;

import com.example.zoo.model.Aviary;
import com.example.zoo.model.Species;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private int id;

    private String name;
    private String gender;
    private int age;
    private Aviary aviary;
    private Species species;

    private Set<FoodDTO> foodSet;
    private Set<EmployeeDTO> employees;

}
