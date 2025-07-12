package Project.Display;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;
import Project.Login.UserLogIn;
import Project.Login.UserSignUp;
import Project.Operations.Operations;

import java.util.Scanner;


public class Display {

    ValidateInput validate = new ValidateInput();
    DaoImpl dao = new DaoImpl();
    UserSignUp userSignUp = new UserSignUp();
    UserLogIn userLogIn = new UserLogIn();
    Operations operations = new Operations();
    ValidateAmount validateAmount = new ValidateAmount();

    UserEntity profile;

    Scanner sc = new Scanner(System.in);

    public void displayAccAccess() {

        System.out.println("=== Account Access ===");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");


        int choice = validate.validateInput(sc, 1, 3);

        switch (choice) {
            case 1:
               for(int attempts = 3; attempts > 0; attempts--) {
                   System.out.print("Enter Username: ");
                   String username = sc.nextLine();

                   System.out.print("Enter Password: ");
                   String password = sc.nextLine();

                   profile = userLogIn.Login(username, password);
                   if (profile != null) {

                       System.out.printf("Welcome, %s %s! %n", profile.getName(), profile.getSurname());
                       displayOperations();
                       break;

                   }else{
                       System.out.printf("Invalid credentials. Attempts left: %d%n", attempts-1);

                       if(attempts > 1){
                           System.out.println("Please try again.\n");
                       }
                       else{
                           System.out.println("Maximum attempts reached. Please try again later...");
                       }
                   }
               }
               break;

            case 2:
                dao.addUser(userSignUp.saveUser());
                System.out.println("Account created! Please Log in.\n");
                displayAccAccess();
                break;


            case 3:
                System.out.println("Exiting...");
                System.exit(0);

        }

    }

    public void displayOperations() {

        while(true) {
            System.out.println("\nOperations: " +
                    "\n1. Deposit" +
                    "\n2. Withdraw" +
                    "\n3. Transfer" +
                    "\n4. View Balance" +
                    "\n5. Exit");

            int choice = validate.validateInput(sc, 1, 5);

            switch (choice) {
                case 1:
                    System.out.print("Enter Amount: ");
                    double depositAmount = validateAmount.validAmount(0, Double.MAX_VALUE, sc);
                    operations.deposit(profile,depositAmount);
                    break;

                case 2:
                    System.out.print("Enter Amount: ");
                    double withdrawAmount = validateAmount.validAmount(0, profile.getBalance(), sc);
                    operations.withdraw(profile,withdrawAmount);
                    break;


                case 3:
                    System.out.print("Enter Receiver ID: ");
                    String receiverId = sc.nextLine();


                    while (dao.getUserById(receiverId) == null) {
                        System.out.printf("User with %s ID not found %n" , receiverId);
                        System.out.print("Enter Receiver ID Again: ");
                        receiverId = sc.nextLine();
                    }

                    System.out.print("Enter Amount: ");
                    double transferAmount = validateAmount.validAmount(0, profile.getBalance(), sc);
                    operations.transfer(profile, receiverId, transferAmount);
                    System.out.println("Transfer successful. New balance: " + profile.getBalance());
                    break;

                case 4:
                    System.out.println("Current Balance: " + profile.getBalance());
                    break;
                case 5:
                    displayAccAccess();
                    return;
            }
        }

    }


}
