package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class AviaryCRUD {
    private static final String Create = "INSERT INTO aviary (size, state) VALUES (?, ?);";
    private static final String Read = "SELECT * FROM aviary;";
    private static final String Update = "UPDATE aviary SET size = ?, state = ? WHERE id = ?;";
    private static final String Delete = "DELETE FROM aviary WHERE id = ?;";

    Scanner scanner = new Scanner(System.in);

    private void createAviary() throws SQLException {
        System.out.print("Введите размер вольера: ");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите состояние вольера: ");
        String state = scanner.nextLine();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Create)) {
            preparedStatement.setInt(1, size);
            preparedStatement.setString(2, state);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void readAviary() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Read)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int size = rs.getInt("size");
                String state = rs.getString("state");
                System.out.println("ID: " + id + ", Размер: " + size + ", Состояние: " + state);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void updateAviary() throws SQLException {
        System.out.print("Введите id вольера: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новый размер вольера: ");
        int size = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новое состояние вольера: ");
        String state = scanner.nextLine();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)) {
            preparedStatement.setInt(1, size);
            preparedStatement.setString(2, state);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void deleteAviary() throws SQLException {
        System.out.print("Введите id вольера: ");
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
            System.out.println("Если хотите добавить вольер, введите 1");
            System.out.println("Если хотите вывести все вольеры, введите 2");
            System.out.println("Если хотите изменить вольер, введите 3");
            System.out.println("Если хотите удалить вольер, введите 4");
            System.out.print("Ввод: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case (1):
                    createAviary();
                    break;
                case (2):
                    readAviary();
                    break;
                case (3):
                    updateAviary();
                    break;
                case (4):
                    deleteAviary();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}