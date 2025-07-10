package Project;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;
import Project.Login.UserSignUp;

import java.util.List;

public class BankApp {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        UserSignUp userSignUp = new UserSignUp();
        dao.addUser(userSignUp.saveUser());

    }
}