package Project.Operations;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;

public class Operations implements OpertionsInterface {
    DaoImpl dao = new DaoImpl();


    @Override
    public void deposit(UserEntity user, double amount) {

        double newBalance = user.getBalance() + amount;
        dao.updateBalance(user.getId(), newBalance);

        user.setBalance(newBalance);
        System.out.println("Deposited. New balance: " + user.getBalance());
    }


    @Override
    public void withdraw(UserEntity user, double amount) {

        double newBalance = user.getBalance() - amount;
        if (newBalance < 0) {
            System.out.println("Withdraw failed. Your balance: " + user.getBalance());

        } else {
            dao.updateBalance(user.getId(), newBalance);
            user.setBalance(newBalance);

            System.out.println("Withdraw successful. New balance: " + user.getBalance());
        }
    }


}
