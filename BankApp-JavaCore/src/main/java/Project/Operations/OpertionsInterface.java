package Project.Operations;

import Project.Entity.UserEntity;

public interface OpertionsInterface {
    public void deposit(UserEntity user, double amount);

    public void withdraw(UserEntity user, double amount);

    public void transfer(UserEntity user, String id ,double amount);
}
