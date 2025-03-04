package com.example.zoo.dto;

import com.example.zoo.model.Aviary;
import com.example.zoo.model.Species;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private int id;

    @NotBlank
    private String name;
    @NotBlank
    private String gender;
    @Positive
    private int age;
    @NotNull
    private Aviary aviary;
    @NotNull
    private Species species;

    private Set<FoodDTO> foodSet;
    private Set<EmployeeDTO> employees;

}
