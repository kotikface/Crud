package servlets;

import servises.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/crud")
public class CrudServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            userService.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("web/select.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        UserService userService = new UserService();
        try {
            userService.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
