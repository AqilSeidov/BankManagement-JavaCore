package Project.Display;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;
import Project.Login.UserLogIn;
import Project.Login.UserSignUp;
import java.util.Scanner;


public class Display {
    ValidateInput validate = new ValidateInput();
    DaoImpl dao = new DaoImpl();
    UserSignUp userSignUp = new UserSignUp();
    UserLogIn userLogIn = new UserLogIn();


    public UserEntity displayAccAccess(Scanner sc) {

        System.out.println("=== Account Access ===");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");

        UserEntity profile = null;
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
                       return profile;

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
                return displayAccAccess(sc);


            case 3:
                System.out.println("Exiting...");
                System.exit(0);

        }
        return profile;
    }


}
