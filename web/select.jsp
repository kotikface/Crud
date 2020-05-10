<%@ page import="servises.UserService" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %>
<%@ page import="exception.DBException" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: SmartBook
  Date: 07.05.2020
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select</title>
</head>
<body>
<form method="POST">
    <input type="text" name="login" placeholder="login"/>
    <input type="text" name="password" placeholder="password"/>
    <input type="text" name="email" placeholder="email"/>
    <input type="text" name="age" placeholder="age"/>
    <button type="submit" formaction="web/insert.jsp">INSERT</button>
    <button type="submit" formaction="web/update.jsp">UPDATE</button>
    <button type="submit" formaction="web/delete.jsp">DELETE</button>
</form>
<%
    UserService userService = new UserService();
    try {
        List<User> list = userService.getAllUser();
        request.setAttribute("list", list);
        response.setStatus(200);
    } catch (DBException e) {
        response.setStatus(400);
    }


%>
<table>
    <tr>
        <th>ID</th>
        <th>login</th>
        <th>Email</th>
        <th>age</th>
    </tr>
    <tbody>
    <!--   for (Todo todo: todos) {  -->
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
