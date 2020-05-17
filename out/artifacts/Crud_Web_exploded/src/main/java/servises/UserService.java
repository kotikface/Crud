package servises;

import dao.UserDAO;
import dao.UserDaoFactory;
import entity.User;
import exception.DBException;

import java.io.*;
import java.util.List;
import java.sql.SQLException;
import java.util.Properties;

public class UserService {

    private static final UserDAO userDAO = UserDaoFactory.createDAO(getPropertyDAO());
    private static UserService userService;
    public static String getPropertyDAO() {
        Properties properties = new Properties();
        String property = null;
        try {
            properties.load(UserService.class.getClassLoader().getResourceAsStream("dao.properties"));
            property = properties.getProperty("daoType");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }



    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            return new UserService();
        } else {
            return userService;
        }
    }

    public boolean deleteClient(long id) throws SQLException {
        if (userDAO.deleteUser(id)) {
            return true;
        } else {
            return false;
        }
    }


    public List<User> getAllUser() throws DBException, SQLException {
        return userDAO.selectUsers();
    }

    public User getUserById(long id) throws SQLException {

        List<User> users = userDAO.selectUsers();
        for (User user1 : users) {
            if (user1.getId() == id) {
                return user1;
            }
        }
        return new User();
    }

    public boolean addUser(User user) throws DBException, SQLException {
        List<User> users = getAllUser();
        if (users.isEmpty()) {
            userDAO.addUser(user);
        } else {
            for (User user1 : users) {
                if (user1.getId() == user.getId()) {
                    return false;
                }
            }
            userDAO.addUser(user);
        }
        return true;
    }

    public boolean updateUser(User user) throws DBException, SQLException {
        return userDAO.updateUser(user);
    }





}
