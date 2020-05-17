package executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedExecutor {

    private final Connection connection;
    private String ex;

    public PreparedExecutor(Connection connection) {
        this.connection = connection;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public void execUpdate(String email, String password, int age, String role) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ex);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        preparedStatement.setInt(3, age);
        preparedStatement.setString(4,role);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
