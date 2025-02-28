package org.example.dto;

public class AnimalEmployee {
    private int animalId;
    private int employeeId;

    public AnimalEmployee(int animalId, int employeeId) {
        this.animalId = animalId;
        this.employeeId = employeeId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}