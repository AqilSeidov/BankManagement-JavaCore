package Project.Login;


import Project.DB.DaoImpl;
import Project.Entity.UserEntity;
import Project.Hashing.HashHandler;

import java.util.Base64;

public class UserLogIn {

    public UserEntity Login(String username, String password) {
        DaoImpl dao = new DaoImpl();
        UserEntity user = dao.getUser(username);

        if(user != null) {
            try{
                byte[] salt = Base64.getDecoder().decode(user.getSalt());
                boolean isPaswordCorrect = HashHandler.verifyPassword(
                        password,
                        user.getPassword(),
                        salt
                );
                if(isPaswordCorrect) {
                    return user;
                }
            }catch (Exception e) {
                System.out.println("Error verifying password --> " + e.getMessage());
            }
        }
        return null;
    }
}
