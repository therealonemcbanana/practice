package org.example.service;

import org.example.JDBCUtils;
import org.example.dto.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodCRUD {

    private static final String CREATE = "INSERT INTO food (name, amount) VALUES (?, ?);";
    private static final String READ = "SELECT * FROM food;";
    private static final String UPDATE = "UPDATE food SET name = ?, amount = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM food WHERE id = ?;";

    public void createFood(String name, int amount) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, amount);
            preparedStatement.executeUpdate();
        }
    }

    public List<Food> readFoods() throws SQLException {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int amount = rs.getInt("amount");
                foods.add(new Food(id, name, amount));
            }
        }
        return foods;
    }

    public void updateFood(int id, String name, int amount) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, amount);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteFood(int id) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}