package servlets;

import dao.UserDAO;
import exception.DBException;
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
        req.getRequestDispatcher("web/select.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "insert":
            {
                req.getRequestDispatcher("web/insert.jsp").forward(req,resp);
            }
            case "update":{
                req.getRequestDispatcher("web/update.jsp").forward(req,resp);
            }
            case "delete":{
                req.getRequestDispatcher("web/delete.jsp").forward(req,resp);
            }
        }

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
