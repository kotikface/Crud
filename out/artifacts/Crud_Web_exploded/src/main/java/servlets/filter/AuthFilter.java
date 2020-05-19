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

@WebFilter("/admin/crud")
public class AuthFilter implements Filter {
    private final UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User userSession = (User) session.getAttribute("user");
        if(userSession == null){
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            try {
                List<User> userList = userService.getAllUser();
                for (User user: userList) {
                    if(login.equals(user.getEmail()) && password.equals(user.getPassword())) {
                        String role = user.getRole();
                        session.setAttribute("user", user);
                        if(role.equals("admin")) {
                            filterChain.doFilter(servletRequest,servletResponse);
                        } else if(role.equals("user")) {
                            res.sendRedirect("/user");
                        }
                    }
                }
            } catch (DBException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            if(userSession.getRole().equals("user")){
                res.sendRedirect("/user");
            } else if(userSession.getRole().equals("admin")){
                filterChain.doFilter(servletRequest, servletResponse);
            }

        }

    }
}
