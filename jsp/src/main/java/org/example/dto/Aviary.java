package org.example.dto;

public class Aviary {
    private int id;
    private int size;
    private String state;

    // Constructor, getters, and setters
    public Aviary(int id, int size, String state) {
        this.id = id;
        this.size = size;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public String getState() {
        return state;
    }
}