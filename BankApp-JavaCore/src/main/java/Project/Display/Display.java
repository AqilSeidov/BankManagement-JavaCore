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

    public static UserEntity profile = null;

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
                    "\n5. Get All Data" +
                    "\n6. Update Data" +
                    "\n7. Exit");

            int choice = validate.validateInput(sc, 1, 7);

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
                    System.out.println("\nId: " + profile.getId()
                            +"\nName: "+ profile.getName()
                            +"\nSurname: "+ profile.getSurname()
                            +"\nAge: "+profile.getAge()
                            +"\nAddress: "+ profile.getAddress()
                            +"\nUsername: "+profile.getUsername()
                            +"\nBalance: "+profile.getBalance());
                    break;


               case 6:
                   int fieldChoice;
                   do{
                   System.out.println("\nSelect Field: " +
                           "\n1. Name" +
                           "\n2. Surname" +
                           "\n3. Username" +
                           "\n4. Password" +
                           "\n5. Age" +
                           "\n6. Address" +
                           "\n7. Save and Exit");
                   fieldChoice = validate.validateInput(sc, 1, 7);
                   switch (fieldChoice) {
                       case 1:
                           profile.setName(userSignUp.checkStr("name"));
                           break;
                       case 2:
                           profile.setSurname(userSignUp.checkStr("surname"));
                           break;
                       case 3:
                           profile.setUsername(userSignUp.checkUsername());
                           break;
                       case 4:
                           profile.setPassword(userSignUp.checkPassword());
                           break;
                       case 5:
                           profile.setAge(userSignUp.checkInt("age"));
                           break;
                       case 6:
                           profile.setAddress(userSignUp.checkMix("address"));
                           break;
                       case 7:
                           dao.update(profile);
                           displayAccAccess();
                           break;
                   }}while(fieldChoice != 7);
                   break;

               case 7:
                    displayAccAccess();
                    return;
            }
        }

    }


}
