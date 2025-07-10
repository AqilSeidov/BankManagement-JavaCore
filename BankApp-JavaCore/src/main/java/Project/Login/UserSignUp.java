package Project.Login;

import Project.Entity.UserEntity;

import java.util.Scanner;

public class UserSignUp {

    public String check(String formatWord,Scanner sc) {
        String word;
        do{
            System.out.printf("Enter your %s: " , formatWord);
            word = sc.nextLine();
            if(word.trim().isEmpty() || !word.matches("[a-zA-Z]+")){
                System.out.printf("You wrote a bad %s. Please enter only letters and don't leave it blank.\n" , formatWord);
            }
        }while(word.trim().isEmpty() || !word.matches("[a-zA-Z]+"));
        return word;
    }

    public void saveUser(){
        UserEntity user = new UserEntity();
        UserSignUp userSignUp = new UserSignUp();
        Scanner input = new Scanner(System.in);

        //Name
        user.setUsername(userSignUp.check("name",input));

        //Surname
        user.setPassword(userSignUp.check("surname",input));

    }
}
