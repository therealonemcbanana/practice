package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class SpeciesCRUD {
    private static final String Create = "INSERT INTO species (title, description) VALUES (?, ?);";
    private static final String Read = "SELECT * FROM species;";
    private static final String Update = "UPDATE species SET title = ?, description = ? WHERE id = ?;";
    private static final String Delete = "DELETE FROM species WHERE id = ?;";

    Scanner scanner = new Scanner(System.in);

    private void createSpecies() throws SQLException {
        System.out.print("Введите название вида: ");
        String title = scanner.nextLine();

        System.out.print("Введите описание вида: ");
        String description = scanner.nextLine();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Create)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void readSpecies() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Read)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                System.out.println("ID: " + id + ", Название: " + title + ", Описание: " + description);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void updateSpecies() throws SQLException {
        System.out.print("Введите id вида: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новое название вида: ");
        String title = scanner.nextLine();

        System.out.print("Введите новое описание вида: ");
        String description = scanner.nextLine();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void deleteSpecies() throws SQLException {
        System.out.print("Введите id вида: ");
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
            System.out.println("Если хотите добавить вид, введите 1");
            System.out.println("Если хотите вывести все виды, введите 2");
            System.out.println("Если хотите изменить вид, введите 3");
            System.out.println("Если хотите удалить вид, введите 4");
            System.out.print("Ввод: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case (1):
                    createSpecies();
                    break;
                case (2):
                    readSpecies();
                    break;
                case (3):
                    updateSpecies();
                    break;
                case (4):
                    deleteSpecies();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}