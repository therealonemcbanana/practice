package org.example.service;

import org.example.JDBCUtils;
import org.example.dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {

    private static final String CREATE = "INSERT INTO employee (name, salary) VALUES (?, ?);";
    private static final String READ = "SELECT * FROM employee;";
    private static final String UPDATE = "UPDATE employee SET name = ?, salary = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM employee WHERE id = ?;";

    public void createEmployee(String name, int salary) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, salary);
            preparedStatement.executeUpdate();
        }
    }

    public List<Employee> readEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                employees.add(new Employee(id, name, salary));
            }
        }
        return employees;
    }

    public void updateEmployee(int id, String name, int salary) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, salary);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}