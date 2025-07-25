package Project.DB;

import Project.Display.Display;
import Project.Entity.UserEntity;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoImpl implements DAO {

    IdGenerator idGenerator = new IdGenerator();


    @Override
    public void addUser(UserEntity user) {

        String sql = "INSERT INTO userdata (id, username,password,name,surname,age,address,balance,salt) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idGenerator.generateId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());
            stmt.setInt(6, user.getAge());
            stmt.setString(7, user.getAddress());
            stmt.setDouble(8, user.getBalance());
            stmt.setString(9, user.getSalt());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public UserEntity getUser(String userName) {
        String sql = "SELECT * FROM userdata WHERE username=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserEntity(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getInt("balance"),
                        rs.getString("salt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public UserEntity getUserById(String id) {
        String sql = "SELECT * FROM userdata WHERE id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserEntity(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getDouble("balance"),
                        rs.getString("salt")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBalance(String id, double newBalance) {
        String sql = "UPDATE userdata SET balance = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setString(2, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Balance update failed.");
        }
    }

    @Override
    public boolean checkUnique(String userName) {
        String sql = "SELECT * FROM userdata WHERE username=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {return false;}
        }
        catch(SQLException e){
                e.printStackTrace();
            }
            return true;

    }

    @Override
    public void update(UserEntity user) {
        String sql = "UPDATE userdata SET name =?,surname = ? , age = ? , address = ?, username=? ,password = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




