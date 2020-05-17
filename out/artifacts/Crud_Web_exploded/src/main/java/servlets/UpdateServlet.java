package servlets;

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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private final UserService userService= UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try {
            User user = userService.getUserById(id);
            req.setAttribute("user", user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("web/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        String role = req.getParameter("role");
        try {
            if (userService.updateUser(new User(id, email, password, age, role))) {
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
            }
        } catch (DBException | SQLException e) {
            resp.setStatus(400);
        }
        resp.sendRedirect("/crud");

    }


}
