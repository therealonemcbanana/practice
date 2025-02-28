package org.example.service;

import org.example.JDBCUtils;
import org.example.dto.AnimalFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalFoodCRUD {

    private static final String CREATE = "INSERT INTO animal_food (food_id, animal_id) VALUES (?, ?);";
    private static final String READ = "SELECT * FROM animal_food;";
    private static final String DELETE = "DELETE FROM animal_food WHERE food_id = ? AND animal_id = ?;";

    public void createAnimalFood(int foodId, int animalId) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, foodId);
            preparedStatement.setInt(2, animalId);
            preparedStatement.executeUpdate();
        }
    }

    public List<AnimalFood> readAnimalFoods() throws SQLException {
        List<AnimalFood> animalFoods = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int foodId = rs.getInt("food_id");
                int animalId = rs.getInt("animal_id");
                animalFoods.add(new AnimalFood(foodId, animalId));
            }
        }
        return animalFoods;
    }

    public void deleteAnimalFood(int foodId, int animalId) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, foodId);
            preparedStatement.setInt(2, animalId);
            preparedStatement.executeUpdate();
        }
    }
}