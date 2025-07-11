package Project.DB;

import Project.Entity.UserEntity;
import org.h2.engine.User;

public interface DAO {

    public void addUser(UserEntity user);

    public UserEntity getUser(String userName, String password);

    public UserEntity getUserById(String id);

    public void updateBalance(String id, double newBalance);

}
