package org.example.service;

import org.example.JDBCUtils;
import org.example.dto.Aviary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AviaryCRUD {

    private static final String CREATE = "INSERT INTO aviary (size, state) VALUES (?, ?);";
    private static final String READ = "SELECT * FROM aviary;";
    private static final String UPDATE = "UPDATE aviary SET size = ?, state = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM aviary WHERE id = ?;";

    public void createAviary(int size, String state) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, size);
            preparedStatement.setString(2, state);
            preparedStatement.executeUpdate();
        }
    }

    public List<Aviary> readAviaries() throws SQLException {
        List<Aviary> aviaries = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int size = rs.getInt("size");
                String state = rs.getString("state");
                aviaries.add(new Aviary(id, size, state));
            }
        }
        return aviaries;
    }

    public void updateAviary(int id, int size, String state) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, size);
            preparedStatement.setString(2, state);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteAviary(int id) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}