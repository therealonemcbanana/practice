package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class AnimalEmployeeCRUD {
    private static final String Create = "INSERT INTO animal_employee (animal_id, employee_id) VALUES (?, ?);";
    private static final String Read = "SELECT * FROM animal_employee;";
    private static final String Update = "UPDATE animal_employee SET employee_id = ?, animal_id = ? WHERE employee_id = ? AND animal_id = ?;";
    private static final String Delete = "DELETE FROM animal_employee WHERE animal_id = ? AND employee_id = ?;";

    Scanner scanner = new Scanner(System.in);

    private void createAnimalEmployee() throws SQLException {
        System.out.print("Введите id животного: ");
        int animalId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите id сотрудника: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Create)) {
            preparedStatement.setInt(1, animalId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void readAnimalEmployee() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Read)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int animalId = rs.getInt("animal_id");
                int employeeId = rs.getInt("employee_id");
                System.out.println("ID животного: " + animalId + ", ID сотрудника: " + employeeId);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void updateAnimalEmployee() throws SQLException {
        System.out.print("Введите текущий id животного: ");
        int oldAnimalId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите текущий id сотрудника: ");
        int oldEmployeeId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новый id животного: ");
        int newAnimalId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новый id сотрудника: ");
        int newEmployeeId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)) {
            preparedStatement.setInt(1, oldEmployeeId);
            preparedStatement.setInt(2, oldAnimalId);
            preparedStatement.setInt(3, newEmployeeId);
            preparedStatement.setInt(4, newAnimalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void deleteAnimalEmployee() throws SQLException {
        System.out.print("Введите id животного: ");
        int animalId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите id сотрудника: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Delete)) {
            preparedStatement.setInt(1, animalId);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void choose() {
        try {
            System.out.println("Если хотите добавить связь животного и сотрудника, введите 1");
            System.out.println("Если хотите вывести все связи, введите 2");
            System.out.println("Если хотите изменить связь, введите 3");
            System.out.println("Если хотите удалить связь, введите 4");
            System.out.print("Ввод: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case (1):
                    createAnimalEmployee();
                    break;
                case (2):
                    readAnimalEmployee();
                    break;
                case (3):
                    updateAnimalEmployee();
                    break;
                case (4):
                    deleteAnimalEmployee();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}