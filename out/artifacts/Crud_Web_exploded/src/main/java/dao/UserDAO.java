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

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
        this.preparedExecutor = new PreparedExecutor(connection);
        this.connection = connection;
    }


    public boolean addUser(User user) {
        try {
            String ex = "INSERT INTO user (`login` ,`password`, `email`, `age`) VALUES (?, ?, ?, ?);";
            preparedExecutor.setEx(ex);
            preparedExecutor.execUpdate(user.getLogin(), user.getPassword(), user.getEmail(), user.getAge());
            return true;
        } catch (SQLException e){
            return false;
        }

    }
    public boolean deleteUser(String login) throws SQLException {
        try {
            executor.execUpdate("Delete from user where login ='"+ login + "'");
            return true;
        } catch (SQLException e){
            return false;
        }

    }
    public List<User> selectUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        executor.execQuery("SELECT * FROM user", result ->{
            while (result.next()){
                User client = new User();
                client.setId(result.getLong(1));
                client.setLogin(result.getString(2));
                client.setPassword(result.getString(3));
                client.setEmail(result.getString(4));
                client.setAge(result.getInt(5));
                users.add(client);
            }
            return users;
        });
        return users;
    }
    public boolean updateUser(User user){
        try{
            String ex = "UPDATE user SET password = ?, email = ?, age = ? where login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(ex);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e ){
            return false;
        }

    }
    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE if not exists   `db_example`.`user` (`id` BIGINT(20) NOT NULL AUTO_INCREMENT," +
                "`login` VARCHAR(256) NOT NULL,`password` VARCHAR(256) NULL,`email` VARCHAR(256) NULL,`age`" +
                " INT NULL, PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE, UNIQUE INDEX " +
                "`login_UNIQUE` (`login` ASC) VISIBLE);");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE IF EXISTS  user");
    }


}
