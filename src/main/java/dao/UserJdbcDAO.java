package dao;

import entity.User;
import executor.Executor;
import executor.PreparedExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Executor executor;
    private final Connection connection;
    private final PreparedExecutor preparedExecutor;
    private static UserDAO userDAO;
    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
        this.preparedExecutor = new PreparedExecutor(connection);
        this.connection = connection;
    }
    public static UserDAO getUserDAO() {
        if (userDAO==null){
            return new UserDAO(getMysqlConnection());
        } else {
            return userDAO;
        }
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


    public boolean addUser(User user) {
        try {
            String ex = "INSERT INTO user (`email`, `password`, `age`) VALUES ( ?, ?, ?);";
            preparedExecutor.setEx(ex);
            preparedExecutor.execUpdate(user.getEmail(), user.getPassword(), user.getAge());
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean deleteUser(long id) throws SQLException {
        try {
            executor.execUpdate("Delete from user where id ='" + id + "'");
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public List<User> selectUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        executor.execQuery("SELECT * FROM user", result -> {
            while (result.next()) {
                User client = new User();
                client.setId(result.getLong(1));
                client.setEmail(result.getString(2));
                client.setPassword(result.getString(3));
                client.setAge(result.getInt(4));
                users.add(client);
            }
            return users;
        });
        return users;
    }

    public boolean updateUser(User user) {
        try {
            String ex = "UPDATE user SET email = ?, password = ?, age = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(ex);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE `db_example`.`user` (\n" +
                "  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
                "  `email` VARCHAR(256) NULL,\n" +
                "  `password` VARCHAR(256) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE IF EXISTS  user");
    }

}
