package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeCRUD {
    private static final String Create = "INSERT INTO employee (name, salary) VALUES (?, ?);";
    private static final String Read = "SELECT * FROM employee;";
    private static final String Update = "UPDATE employee SET name = ?, salary = ? WHERE id = ?;";
    private static final String Delete = "DELETE FROM employee WHERE id = ?;";

    Scanner scanner = new Scanner(System.in);

    private void createEmployee() throws SQLException {
        System.out.print("Введите имя сотрудника: ");
        String name = scanner.nextLine();

        System.out.print("Введите зарплату сотрудника: ");
        int salary = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Create)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, salary);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void readEmployee() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Read)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                System.out.println("ID сотрудника: " + id + ", Имя: " + name + ", Зарплата: " + salary);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void updateEmployee() throws SQLException {
        System.out.print("Введите id сотрудника: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новое имя сотрудника: ");
        String name = scanner.nextLine();

        System.out.print("Введите новую зарплату сотрудника: ");
        int salary = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, salary);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void deleteEmployee() throws SQLException {
        System.out.print("Введите id сотрудника: ");
        int id = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Delete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void choose() {
        try {
            System.out.println("Если хотите добавить сотрудника, введите 1");
            System.out.println("Если хотите вывести всех сотрудников, введите 2");
            System.out.println("Если хотите изменить сотрудника, введите 3");
            System.out.println("Если хотите удалить сотрудника, введите 4");
            System.out.print("Ввод: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case (1):
                    createEmployee();
                    break;
                case (2):
                    readEmployee();
                    break;
                case (3):
                    updateEmployee();
                    break;
                case (4):
                    deleteEmployee();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
