package Project.DB;

import Project.Entity.UserEntity;

import java.sql.*;

public class DaoImpl implements DAO {

    IdGenerator idGenerator = new IdGenerator();

    @Override
    public void addUser(UserEntity user) {

        String sql = "INSERT INTO userdata (id, username,password,name,surname,age,address,balance) VALUES (?,?,?,?,?,?,?,?)";

        try(Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,idGenerator.generateId());
            stmt.setString(2,user.getUsername());
            stmt.setString(3,user.getPassword());
            stmt.setString(4,user.getName());
            stmt.setString(5,user.getSurname());
            stmt.setInt(6,user.getAge());
            stmt.setString(7,user.getAddress());
            stmt.setInt(8,user.getBalance());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully Registered");
    }

}

