package dao;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    boolean addUser(User user);
    boolean deleteUser(long id);
    List<User> selectUsers() throws SQLException;
    boolean updateUser(User user);



}
