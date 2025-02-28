package org.example.service;

import org.example.JDBCUtils;
import org.example.dto.AnimalEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalEmployeeCRUD {

    private static final String CREATE = "INSERT INTO animal_employee (animal_id, employee_id) VALUES (?, ?);";
    private static final String READ = "SELECT * FROM animal_employee;";
    private static final String DELETE = "DELETE FROM animal_employee WHERE animal_id = ? AND employee_id = ?;";

    public void createAnimalEmployee(int animalId, int employeeId) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, animalId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
        }
    }

    public List<AnimalEmployee> readAnimalEmployees() throws SQLException {
        List<AnimalEmployee> animalEmployees = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int animalId = rs.getInt("animal_id");
                int employeeId = rs.getInt("employee_id");
                animalEmployees.add(new AnimalEmployee(animalId, employeeId));
            }
        }
        return animalEmployees;
    }

    public void deleteAnimalEmployee(int animalId, int employeeId) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, animalId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
        }
    }
}