package Project.DB;

import Project.Entity.UserEntity;
import org.h2.engine.User;

public interface DAO {
    public void addUser(UserEntity user);

}
