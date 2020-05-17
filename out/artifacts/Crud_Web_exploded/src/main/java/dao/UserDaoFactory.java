package dao;

import java.sql.SQLException;
import java.util.Properties;

public class UserDaoFactory  {

    public static UserDAO createDAO(String  property)  {
        if (property.equalsIgnoreCase("UserHibernateDAO")){
            return UserHibernateDAO.getUserHibernateDAO();
        } else if (property.equalsIgnoreCase("UserJdbcDAO")){
            return UserJdbcDAO.getUserJdbcDAO();
        }else {
            throw new RuntimeException("unknown property");
        }
    }
}
