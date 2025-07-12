package Project.Operations;

import Project.DB.DaoImpl;
import Project.Entity.UserEntity;

public class Operations implements OperationsInterface {
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

        dao.updateBalance(user.getId(), newBalance);
        user.setBalance(newBalance);

        System.out.println("Withdraw successful. New balance: " + user.getBalance());

    }


    @Override
    public void transfer(UserEntity user, String transferId, double amount) {

        UserEntity transferProfile = dao.getUserById(transferId);

                //Decrement
                user.setBalance(user.getBalance() - amount);
                dao.updateBalance(user.getId(), user.getBalance());

                //Increment
                transferProfile.setBalance(transferProfile.getBalance() + amount);
                dao.updateBalance(transferProfile.getId(), transferProfile.getBalance());

        }
    }

