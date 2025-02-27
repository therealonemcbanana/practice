package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class FoodCRUD {
    private static final String Create = "INSERT INTO food (name, amount) VALUES (?, ?);";
    private static final String Read = "SELECT * FROM food;";
    private static final String Update = "UPDATE food SET name = ?, amount = ? WHERE id = ?;";
    private static final String Delete = "DELETE FROM food WHERE id = ?;";

    Scanner scanner = new Scanner(System.in);

    private void createFood() throws SQLException {
        System.out.print("Введите название корма: ");
        String name = scanner.nextLine();

        System.out.print("Введите количество корма: ");
        int amount = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Create)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, amount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void readFood() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Read)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int amount = rs.getInt("amount");
                System.out.println("ID: " + id + ", Название: " + name + ", Количество: " + amount);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void updateFood() throws SQLException {
        System.out.print("Введите id корма: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новое название корма: ");
        String name = scanner.nextLine();

        System.out.print("Введите новое количество корма: ");
        int amount = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, amount);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void deleteFood() throws SQLException {
        System.out.print("Введите id корма: ");
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
            System.out.println("Если хотите добавить корм, введите 1");
            System.out.println("Если хотите вывести все корма, введите 2");
            System.out.println("Если хотите изменить корм, введите 3");
            System.out.println("Если хотите удалить корм, введите 4");
            System.out.print("Ввод: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case (1):
                    createFood();
                    break;
                case (2):
                    readFood();
                    break;
                case (3):
                    updateFood();
                    break;
                case (4):
                    deleteFood();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}