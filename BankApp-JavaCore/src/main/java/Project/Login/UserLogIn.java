package Project.Login;

import Project.DB.DAO;
import Project.DB.DaoImpl;
import Project.Entity.UserEntity;

public class UserLogIn {

    public UserEntity Login(String username, String password) {
        DaoImpl dao = new DaoImpl();
        UserEntity user = dao.getUser(username, password);

        if (user != null) {
            return user;

        }else {
            return null;
        }
    }
}
