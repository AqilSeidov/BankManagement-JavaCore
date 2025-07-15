package Project.Login;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;

import java.util.Scanner;

public class UserSignUp {
    Scanner sc = new Scanner(System.in);
    DaoImpl dao = new DaoImpl();

    public String checkStr(String formatWord) {
        String word;
        do{
            System.out.printf("Enter your %s: " , formatWord);
            word = sc.nextLine();
            if(word.trim().isEmpty() || !word.matches("[a-zA-Z. ]+")){
                System.out.printf("Invalid %s. Please enter only letters and don't leave it blank.\n" , formatWord);
            }
        }while(word.trim().isEmpty() || !word.matches("[a-zA-Z. ]+"));
        return word;
    }


    public int checkInt(String formatWord){
        int number;

        while(true){
            System.out.printf("Enter your %s: " , formatWord);

            while (!sc.hasNextInt()) {
                System.out.print("Please enter an integer: ");
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

    public String checkMix(String formatWord){
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

    public String checkUsername() {
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        String regex = "^(?=.*\\d)[A-Za-z][A-Za-z0-9_]{6,14}$";

        while (!username.matches(regex)) {
            System.out.println("Invalid username. It must:\n" +
                    "- Be 7–15 characters long\n" +
                    "- Start with a letter\n" +
                    "- Have at least one digit\n" +
                    "- Contain only letters, numbers, or underscores\n");
            System.out.print("Please enter a valid username: ");
            username = sc.nextLine();
        }

        return username;
    }
    public String checkPassword() {
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./]).{6,}$";
        while (!password.matches(regex)) {
            System.out.println("Invalid Password. It must:\n" +
                    "- Be at least 6 characters long\n" +
                    "- Have at least one uppercase and lowercase letter\n" +
                    "- Have at least one digit\n" +
                    "- Have at least one symbol\n");
            System.out.print("Please enter a valid password: ");
            password = sc.nextLine();
        }
        return password;
    }


    public UserEntity saveUser(){
        UserEntity user = new UserEntity();
        Scanner input = new Scanner(System.in);

        //Name
        user.setName(checkStr("name"));

        //Surname
        user.setSurname(checkStr("surname"));

        //Age
        user.setAge(checkInt("age"));

        //address
        user.setAddress(checkMix("address"));

        //username

        user.setUsername(checkUsername());
        boolean check = dao.checkUnique(user.getUsername());

        while(!check){
            System.out.println("Username already exists.");
            user.setUsername(checkUsername());
            check = dao.checkUnique(user.getUsername());
        }


        //password
        user.setPassword(checkPassword());

        //balance
        user.setBalance(checkInt("balance"));

        return user;
    }
}
