package org.example.service;

import org.example.JDBCUtils;
import org.example.dto.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalCRUD {

    private static final String CREATE = "INSERT INTO animal (name, gender, age, aviary_id, species_id) VALUES (?, ?, ?, ?, ?);";
    private static final String READ = "SELECT * FROM animal;";
    private static final String UPDATE = "UPDATE animal SET name = ?, gender = ?, age = ?, aviary_id = ?, species_id = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM animal WHERE id = ?;";

    public void createAnimal(String name, String gender, int age, int aviaryId, int speciesId) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, aviaryId);
            preparedStatement.setInt(5, speciesId);
            preparedStatement.executeUpdate();
        }
    }

    public List<Animal> readAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int aviaryId = rs.getInt("aviary_id");
                int speciesId = rs.getInt("species_id");
                animals.add(new Animal(id, name, gender, age, aviaryId, speciesId));
            }
        }
        return animals;
    }

    public void updateAnimal(int id, String name, String gender, int age, int aviaryId, int speciesId) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, aviaryId);
            preparedStatement.setInt(5, speciesId);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteAnimal(int id) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}