package com.example.zoo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private int id;
    @NotBlank
    private String name;
    @PositiveOrZero
    private int amount;
}
