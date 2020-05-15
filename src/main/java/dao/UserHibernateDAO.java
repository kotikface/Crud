package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    public static final SessionFactory sessionFactory = DBHelper.getSessionFactory();
    private Session session;
    public static UserHibernateDAO userHibernateDAO;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public static UserHibernateDAO getUserHibernateDAO() {

        if (userHibernateDAO == null) {
            return new UserHibernateDAO(sessionFactory.openSession());
        } else {
            return userHibernateDAO;
        }
    }

    @Override
    public boolean addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteUser(long id) {
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> selectUsers() throws SQLException {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }
}
