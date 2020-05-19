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
import java.util.List;

@WebServlet("/admin/crud")
public class CrudServlet extends HttpServlet  {
    private final UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<User> list = userService.getAllUser();
            req.setAttribute("list", list);
            resp.setStatus(200);
        } catch (DBException | SQLException e) {
            resp.setStatus(400);
        }
        req.getRequestDispatcher("/web/select.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        String role = req.getParameter("role");
        try {
            if (userService.addUser(new User(email, password, age, role))) {
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
            }
        } catch (DBException | SQLException e) {
            resp.setStatus(400);
        }
        resp.sendRedirect("/admin/crud");
    }

}
