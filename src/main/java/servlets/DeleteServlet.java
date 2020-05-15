package servlets;

import entity.User;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long  id = Long.parseLong(req.getParameter("id"));
        try {
            if (userService.deleteClient(id)) {
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
            }
        } catch (SQLException e) {
            resp.setStatus(400);
        }
        resp.sendRedirect("/crud");
    }
}
