package org.example.dto;

public class Animal {
    private int id;
    private String name;
    private String gender;
    private int age;
    private int aviaryId;
    private int speciesId;

    // Конструктор, геттеры и сеттеры
    public Animal(int id, String name, String gender, int age, int aviaryId, int speciesId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.aviaryId = aviaryId;
        this.speciesId = speciesId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getAviaryId() {
        return aviaryId;
    }

    public int getSpeciesId() {
        return speciesId;
    }
}