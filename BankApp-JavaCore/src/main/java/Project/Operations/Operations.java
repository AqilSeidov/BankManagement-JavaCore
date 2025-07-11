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
        if (newBalance < 0) {
            System.out.println("Withdraw failed. Your balance: " + user.getBalance());

        } else {
            dao.updateBalance(user.getId(), newBalance);
            user.setBalance(newBalance);

            System.out.println("Withdraw successful. New balance: " + user.getBalance());
        }
    }


    @Override
    public void transfer(UserEntity user, String transferId, double amount) {

        UserEntity transferProfile = dao.getUserById(transferId);

        if(transferProfile == null) {
            System.out.printf("User with %s ID not found %n" , transferId);
        }
        else {
            if (user.getBalance() < amount) {
                System.out.println("Insufficient balance. Your balance: " + user.getBalance());
            } else {
                //Decrement
                user.setBalance(user.getBalance() - amount);
                dao.updateBalance(user.getId(), user.getBalance());

                //Increment
                transferProfile.setBalance(transferProfile.getBalance() + amount);
                dao.updateBalance(transferProfile.getId(), transferProfile.getBalance());

                System.out.println("Transfer successful. New balance: " + user.getBalance());
            }
        }
    }


}
