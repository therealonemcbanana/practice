package org.example.dto;

public class AnimalFood {
    private int foodId;
    private int animalId;

    public AnimalFood(int foodId, int animalId) {
        this.foodId = foodId;
        this.animalId = animalId;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getAnimalId() {
        return animalId;
    }
}