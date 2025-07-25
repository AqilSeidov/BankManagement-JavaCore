package Project.DB;

import Project.Entity.UserEntity;


public interface DAO {

    public void addUser(UserEntity user);

    public UserEntity getUser(String userName);

    public UserEntity getUserById(String id);

    public void updateBalance(String id, double newBalance);

    public boolean checkUnique(String userName);

    public void update(UserEntity user);

}
