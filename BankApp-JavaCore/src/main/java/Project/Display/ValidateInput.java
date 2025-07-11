package Project.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidateInput {

    public int validateInput(Scanner sc, int min, int max) {
        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice (" + min + "-" + max + "): ");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
}
