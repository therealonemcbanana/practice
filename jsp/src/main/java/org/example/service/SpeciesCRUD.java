package org.example.service;

import org.example.JDBCUtils;
import org.example.dto.Species;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpeciesCRUD {

    private static final String CREATE = "INSERT INTO species (title, description) VALUES (?, ?);";
    private static final String READ = "SELECT * FROM species;";
    private static final String UPDATE = "UPDATE species SET title = ?, description = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM species WHERE id = ?;";

    public void createSpecies(String title, String description) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        }
    }

    public List<Species> readSpecies() throws SQLException {
        List<Species> speciesList = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                speciesList.add(new Species(id, title, description));
            }
        }
        return speciesList;
    }

    public void updateSpecies(int id, String title, String description) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteSpecies(int id) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}