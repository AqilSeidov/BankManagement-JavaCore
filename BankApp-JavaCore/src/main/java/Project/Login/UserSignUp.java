package Project.Login;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;

import java.util.Scanner;

public class UserSignUp {

    DaoImpl dao = new DaoImpl();

    public String checkStr(String formatWord,Scanner sc) {
        String word;
        do{
            System.out.printf("Enter your %s: " , formatWord);
            word = sc.nextLine();
            if(word.trim().isEmpty() || !word.matches("[a-zA-Z]+")){
                System.out.printf("Invalid %s. Please enter only letters and don't leave it blank.\n" , formatWord);
            }
        }while(word.trim().isEmpty() || !word.matches("[a-zA-Z]+"));
        return word;
    }


    public int checkInt(String formatWord, Scanner sc){
        int number;

        while(true){
            System.out.printf("Enter your %s: " , formatWord);

            while (!sc.hasNextInt()) {
                System.out.println("Please enter an integer.");
                sc.next();
            }
            number = sc.nextInt();
            sc.nextLine();

            if(number <0){
                System.out.println("Enter an integer greater than 0.");
                continue;
            }
            if (formatWord.equals("age") && number <18) {
                System.out.println("You must be at least 18 years old.");
                continue;
            }
            break;

        }
        return number;
    }

    public String checkMix(String formatWord, Scanner sc){
        String word;
        while(true){
            System.out.printf("Enter your %s: " , formatWord);
            word = sc.nextLine();

            if(word.trim().isEmpty() || !word.matches("^[A-Za-z0-9_.\\- ]+$")){
                System.out.printf("Invalid %s. Please don't leave it blank and use only letters, positive numbers and '_','.','-' symbols.\n", formatWord);

            } else break;

        }

        return word;
    }

    public String checkUsername(Scanner sc) {
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        String regex = "^[A-Za-z][A-Za-z0-9_]{4,14}$";

        while (!username.matches(regex)) {
            System.out.println("Invalid username. It must:\n" +
                    "- Be 5–15 characters long\n" +
                    "- Start with a letter\n" +
                    "- Contain only letters, numbers, or underscores\n");
            System.out.print("Please enter a valid username: ");
            username = sc.nextLine();
        }

        return username;
    }




    public UserEntity saveUser(){
        UserEntity user = new UserEntity();
        Scanner input = new Scanner(System.in);

        //Name
        user.setName(checkStr("name",input));

        //Surname
        user.setSurname(checkStr("surname",input));

        //Age
        user.setAge(checkInt("age",input));

        //address
        user.setAddress(checkMix("address",input));

        //username

        user.setUsername(checkUsername(input));
        boolean check = dao.checkUnique(user.getUsername());

        while(!check){
            System.out.println("Username already exists.");
            user.setUsername(checkMix("username",input));
            check = dao.checkUnique(user.getUsername());
        }


        //password
        user.setPassword(checkMix("password",input));

        //balance
        user.setBalance(checkInt("balance",input));

        return user;
    }
}
