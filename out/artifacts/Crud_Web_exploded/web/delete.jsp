<%--
  Created by IntelliJ IDEA.
  User: SmartBook
  Date: 07.05.2020
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<%@ page import="servises.UserService"%>
<%@ page import="java.sql.SQLException" %>
<%
   String login =request.getParameter("login");
    UserService userService = new UserService();
    try {
        if(userService.deleteClient(login)){
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    } catch (SQLException e) {
        response.setStatus(400);
    }
    response.sendRedirect("/crud");
%>
</body>
</html>
