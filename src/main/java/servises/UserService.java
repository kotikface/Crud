package servises;

import dao.UserDAO;
import dao.UserHibernateDAO;
import entity.User;
import exception.DBException;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;
import java.sql.SQLException;

public class UserService {

    private static UserService userService;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            return new UserService(DBHelper.getSessionFactory());
        } else {
            return userService;
        }
    }

    public boolean deleteClient(long id) throws SQLException {
        UserDAO userDAO = UserHibernateDAO.getUserHibernateDAO();

        if (userDAO.deleteUser(id)) {
            return true;
        } else {
            return false;
        }
    }


    public List<User> getAllUser() throws DBException, SQLException {
        UserDAO userDAO = UserHibernateDAO.getUserHibernateDAO();
        return userDAO.selectUsers();
    }

    public User getUserById(long id) throws SQLException {
        UserDAO userDAO = UserHibernateDAO.getUserHibernateDAO();
        List<User> users = userDAO.selectUsers();
        for (User user1 : users) {
            if (user1.getId() == id) {
                return user1;
            }
        }
        return new User();
    }

    public boolean addUser(User user) throws DBException, SQLException {
        UserDAO userDAO = UserHibernateDAO.getUserHibernateDAO();
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
        UserDAO userDAO = UserHibernateDAO.getUserHibernateDAO();
        return userDAO.updateUser(user);
    }





}
