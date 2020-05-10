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

    public void execUpdate(String login, String password, String email, int age) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(ex);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
}
