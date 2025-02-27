package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class AnimalFoodCRUD {
    private static final String Create = "INSERT INTO animal_food (food_id, animal_id) VALUES (?, ?);";
    private static final String Read = "SELECT * FROM animal_food;";
    private static final String Update = "UPDATE animal_food SET food_id = ?, animal_id = ? WHERE food_id = ? AND animal_id = ?;";
    private static final String Delete = "DELETE FROM animal_food WHERE food_id = ? AND animal_id = ?;";

    Scanner scanner = new Scanner(System.in);

    public void createAnimalFood() throws SQLException {
        System.out.print("Введите id корма: ");
        int foodId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите id животного: ");
        int animalId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Create)) {
            preparedStatement.setInt(1, foodId);
            preparedStatement.setInt(2, animalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void readAnimalFood() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Read)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int foodId = rs.getInt("food_id");
                int animalId = rs.getInt("animal_id");
                System.out.println("ID корма: " + foodId + ", ID животного: " + animalId);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void updateAnimalFood() throws SQLException {
        System.out.print("Введите текущий id корма: ");
        int oldFoodId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите текущий id животного: ");
        int oldAnimalId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новый id корма: ");
        int newFoodId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новый id животного: ");
        int newAnimalId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)) {
            preparedStatement.setInt(1, newFoodId);
            preparedStatement.setInt(2, newAnimalId);
            preparedStatement.setInt(3, oldFoodId);
            preparedStatement.setInt(4, oldAnimalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void deleteAnimalFood() throws SQLException {
        System.out.print("Введите id корма: ");
        int foodId = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите id животного: ");
        int animalId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Delete)) {
            preparedStatement.setInt(1, foodId);
            preparedStatement.setInt(2, animalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void choose() {
        try {
            System.out.println("Если хотите добавить связь корма и животного, введите 1");
            System.out.println("Если хотите вывести все связи кормов и животных, введите 2");
            System.out.println("Если хотите изменить связь корма и животного, введите 3");
            System.out.println("Если хотите удалить связь корма и животного, введите 4");
            System.out.print("Ввод: ");

            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case (1):
                    createAnimalFood();
                    break;
                case (2):
                    readAnimalFood();
                    break;
                case (3):
                    updateAnimalFood();
                    break;
                case (4):
                    deleteAnimalFood();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}