package com.example.zoo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal_food", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"food_id", "animal_id"})
})
public class AnimalFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
}