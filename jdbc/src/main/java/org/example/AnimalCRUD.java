package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class AnimalCRUD {
    private static final String Create = "INSERT INTO animal (name, gender, age, aviary_id, species_id) VALUES (?, ?, ?, ?, ?);";
    private static final String Read = "SELECT * FROM animal;";
    private static final String Update = "UPDATE animal SET name = ?, gender = ?, age = ?, aviary_id = ?, species_id = ? WHERE id = ?;";
    private static final String Delete = "DELETE FROM animal WHERE id = ?;";

    Scanner scanner = new Scanner(System.in);

    public void createAnimal() throws SQLException {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите пол животного: ");
        String gender = scanner.nextLine();

        System.out.print("Введите возраст животного: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите id вольера: ");
        int aviaryId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите id вида: ");
        int speciesId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Create)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, aviaryId);
            preparedStatement.setInt(5, speciesId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void readAnimal() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Read)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int aviaryId = rs.getInt("aviary_id");
                int speciesId = rs.getInt("species_id");
                System.out.println("ID животного: " + id + ", Имя: " + name + ", Пол: " + gender + ", Возраст: " + age + ", ID вольера: " + aviaryId + ", ID вида: " + speciesId);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void updateAnimal() throws SQLException {
        System.out.print("Введите id животного: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новое имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите новый пол животного: ");
        String gender = scanner.nextLine();

        System.out.print("Введите новый возраст животного: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новый id вольера: ");
        int aviaryId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новый id вида: ");
        int speciesId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, aviaryId);
            preparedStatement.setInt(5, speciesId);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void deleteAnimal() throws SQLException {
        System.out.print("Введите id животного: ");
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
            System.out.println("Если хотите добавить животное, введите 1");
            System.out.println("Если хотите вывести всех животных, введите 2");
            System.out.println("Если хотите изменить животное, введите 3");
            System.out.println("Если хотите удалить животное, введите 4");
            System.out.print("Ввод: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case (1):
                    createAnimal();
                    break;
                case (2):
                    readAnimal();
                    break;
                case (3):
                    updateAnimal();
                    break;
                case (4):
                    deleteAnimal();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}