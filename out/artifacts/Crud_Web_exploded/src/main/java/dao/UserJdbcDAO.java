package dao;

import entity.User;
import executor.Executor;
import executor.PreparedExecutor;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private final Executor executor;
    private final Connection connection;
    private final PreparedExecutor preparedExecutor;
    private static UserJdbcDAO userJdbcDAO;
    public UserJdbcDAO(Connection connection) {
        this.executor = new Executor(connection);
        this.preparedExecutor = new PreparedExecutor(connection);
        this.connection = connection;
    }
    public static UserJdbcDAO getUserJdbcDAO() {
        if (userJdbcDAO ==null){
            return new UserJdbcDAO(DBHelper.getMysqlConnection());
        } else {
            return userJdbcDAO;
        }
    }

    @Override
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

    @Override
    public boolean deleteUser(long id) {
        try {
            executor.execUpdate("Delete from user where id ='" + id + "'");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
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

    @Override
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
