package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();
        AnimalCRUD animalCRUD = new AnimalCRUD();
        AviaryCRUD aviaryCRUD = new AviaryCRUD();
        FoodCRUD foodCRUD = new FoodCRUD();
        SpeciesCRUD speciesCRUD = new SpeciesCRUD();
        AnimalEmployeeCRUD animalEmployeeCRUD = new AnimalEmployeeCRUD();
        AnimalFoodCRUD animalFoodCRUD = new AnimalFoodCRUD();

        while (true) {
            System.out.println("Если хотите выйти *@&#!, введите 0");
            System.out.println("Если хотите работать с табдицей animal, введите 1");
            System.out.println("Если хотите работать с табдицей animal_employee, введите 2");
            System.out.println("Если хотите работать с табдицей animal_food, введите 3");
            System.out.println("Если хотите работать с табдицей aviary, введите 4");
            System.out.println("Если хотите работать с табдицей employee, введите 5");
            System.out.println("Если хотите работать с табдицей food, введите 6");
            System.out.println("Если хотите работать с табдицей species, введите 7");
            System.out.print("Ввод: ");
            int table = Integer.parseInt(scanner.nextLine());

            switch (table) {
                case (0):
                    return;
                case (1):
                    animalCRUD.choose();
                    break;
                case (2):
                    animalEmployeeCRUD.choose();
                    break;
                case (3):
                    animalFoodCRUD.choose();
                    break;
                case (4):
                    aviaryCRUD.choose();
                    break;
                case (5):
                    employeeCRUD.choose();
                    break;
                case (6):
                    foodCRUD.choose();
                    break;
                case (7):
                    speciesCRUD.choose();
                    break;
                default:
                    System.out.println("Неверный ввод!");
            }
        }
    }
}