package servlets;

import dao.UserDaoFactory;
import dao.UserJdbcDAO;
import entity.User;
import exception.DBException;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("")
public class LoginServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    public void init() throws ServletException {
        try {
            userService.createTable();
            userService.addUser(new User("admin", "admin", 23, "admin"));
            userService.addUser(new User("user", "user", 23, "user"));
        } catch (SQLException | DBException ignored) { }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("web/login.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        try {
            userService.dropTable();
        } catch (SQLException ignored) { }
    }
}

