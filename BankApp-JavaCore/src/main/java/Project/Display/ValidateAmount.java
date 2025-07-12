package Project.Display;

import java.util.Scanner;

public class ValidateAmount {

    public double validAmount(double min, double max, Scanner sc) {
        while (true) {
            try {
                double amount = Double.parseDouble(sc.nextLine());
                if (amount < min) {
                    System.out.println("Amount cannot be negative");
                } else if (amount > max) {
                    System.out.printf("Insufficient balance, Balance: %2.2f%n", max);
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            System.out.print("Please enter amount again: ");
        }
    }
}

