package servlets.filter;

import dao.UserDAO;
import dao.UserJdbcDAO;
import entity.User;
import exception.DBException;
import servises.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebFilter("/filter")
public class AuthFilter implements Filter {
    private final UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            if (UserService.getPropertyDAO().equalsIgnoreCase("UserJdbcDAO")){
                try {
                    UserJdbcDAO.getUserJdbcDAO().createTable();
                } catch (SQLException ignored) { }
            }
            userService.addUser(new User( "admin", "admin", 23, "admin" ));
            userService.addUser(new User("user", "user", 23, "user"));
        } catch (DBException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            List<User> userList = userService.getAllUser();
            for (User user: userList) {
                if(login.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    String role = user.getRole();
                    if(role.equals("admin")){
                        session.setAttribute("user", user);
                        res.sendRedirect("/crud");
                    } else if(role.equals("user")) {
                        session.setAttribute("user", user);
                        res.sendRedirect("web/user.jsp");
                    }
                }
            }
        } catch (DBException | SQLException e) {
            e.printStackTrace();
        }
    }
}
