package Project.Operations;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;
import Project.Login.UserLogIn;

import java.util.Scanner;

public class Deposit {
    DaoImpl dao = new DaoImpl();
    public void Deposit(UserEntity user, int amount) {

        int newBalance = user.getBalance() + amount;
        dao.updateBalance(user.getId(), newBalance);

        user.setBalance(newBalance);
        System.out.println("Deposited. New balance: " + user.getBalance());
    }

}
