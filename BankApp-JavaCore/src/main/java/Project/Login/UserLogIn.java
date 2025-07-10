package Project.Login;

import Project.DB.DAO;
import Project.DB.DaoImpl;
import Project.Entity.UserEntity;

public class UserLogIn {

    public UserEntity Login(String username, String password) {
        DaoImpl dao = new DaoImpl();
        UserEntity user = dao.getUser(username, password);
        if (user != null) {
            System.out.printf("User: %s %s logged in %n", user.getName(), user.getSurname());
            return user;

        }else {
            System.out.println("Login failed: Incorrect username or password");
            return null;
        }
    }
}
