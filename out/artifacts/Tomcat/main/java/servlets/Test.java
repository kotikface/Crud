package servlets;

import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/test")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().println(PageGenerator.getInstance().getPage("firstJsp.jsp", new HashMap<>()));
        resp.getWriter().write("f");
    }
}
