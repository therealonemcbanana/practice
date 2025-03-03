package com.example.zoo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "gender")
    private String gender;

    @Positive
    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "aviary_id", nullable = false)
    private Aviary aviary;

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;
}