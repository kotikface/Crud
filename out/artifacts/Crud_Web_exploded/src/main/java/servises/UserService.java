package servises;

import dao.UserDAO;
import entity.User;
import exception.DBException;
import java.util.List;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {
    public boolean deleteClient(String login) throws SQLException {
        if(getUserDAO().deleteUser(login)){
            return true;
        } else {
            return false;
        }
    }
    public List<User> getAllUser() throws DBException {
        try {
            return  getUserDAO().selectUsers();
        } catch (SQLException e) {
            throw  new DBException(e);
        }
    }
    public boolean addUser(User user) throws DBException, SQLException {
        List<User> users = getAllUser();
        if(users.isEmpty()){
            getUserDAO().addUser(user);
        } else {
            for (User user1: users) {
                if (user1.getLogin().equals(user.getLogin())){
                    return false;
                }
            }
            getUserDAO().addUser(user);
        }
        return true;
    }
    public boolean updateUser(User user) throws DBException, SQLException {
        return getUserDAO().updateUser(user);
    }
    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=Vadim111555999q");


            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
    public void createTable() throws SQLException {
        getUserDAO().createTable();
    }
    public void dropTable() throws  SQLException{
        getUserDAO().dropTable();
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
