<%@ page import="servises.UserService" %>
<%@ page import="entity.User" %>
<%@ page import="exception.DBException" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: SmartBook
  Date: 07.05.2020
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert</title>
</head>
<body>
<%
    UserService userService = new UserService();
    String login = request.getParameter("login");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    int age = Integer.parseInt(request.getParameter("age"));
    try {
        if (userService.addUser(new User(login, password, email, age))) {
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    } catch (DBException | SQLException e) {
        response.setStatus(400);
    }
    response.sendRedirect("/crud");
%>
</body>
</html>
