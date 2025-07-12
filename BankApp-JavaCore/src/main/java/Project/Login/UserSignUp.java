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

            if(formatWord.equals("address")){

                if(word.trim().isEmpty() || !word.matches("^[A-Za-z0-9_.\\- ]+$")){
                    System.out.printf("Invalid %s. Please don't leave it blank and use only letters, positive numbers and '_','.','-' symbols.\n", formatWord);

                } else break;
            }
            else{

                if(word.trim().isEmpty() || !word.matches("^[A-Za-z0-9_.-]+$")){
                    System.out.printf("Invalid %s. Please don't leave it blank and use only letters, positive numbers and '_','.','-' symbols.\n", formatWord);

                }else break;
            }
        }
        return word;
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
        user.setUsername(checkMix("username",input));
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
