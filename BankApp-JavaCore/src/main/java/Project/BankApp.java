package Project;

import Project.DB.DaoImpl;
import Project.Display.Display;
import Project.Entity.UserEntity;
import Project.Login.UserLogIn;
import Project.Login.UserSignUp;
import Project.Operations.Operations;
import org.h2.engine.Database;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Display display = new Display();
        display.displayAccAccess(sc);

    }
}