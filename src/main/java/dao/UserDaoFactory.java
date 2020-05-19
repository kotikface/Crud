package dao;

import servises.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class UserDaoFactory  {

    public static UserDAO createDAO()  {
        String property = getPropertyDAO();
        if (property.equalsIgnoreCase("UserHibernateDAO")){
            return UserHibernateDAO.getUserHibernateDAO();
        } else if (property.equalsIgnoreCase("UserJdbcDAO")){
            return UserJdbcDAO.getUserJdbcDAO();
        }else {
            throw new RuntimeException("unknown property");
        }
    }
    private static String getPropertyDAO() {
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
}
