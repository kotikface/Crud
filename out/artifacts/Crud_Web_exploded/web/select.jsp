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
<h2>CRUD</h2>
<form method="POST">
    <input type="text" name="email" placeholder="email"/>
    <input type="text" name="password" placeholder="password"/>
    <input type="text" name="age" placeholder="age"/>
    <button type="submit" formaction="/crud">INSERT</button>

    <table>
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>age</th>
        </tr>
        <tbody>
        <!--   for (Todo todo: todos) {  -->
        <c:forEach items="${requestScope.list}" var="user1">
            <tr>
                <td>${user1.id}</td>
                <td>${user1.email}</td>
                <td>${user1.age}</td>

                <td>
                    <a href="/update?id=${user1.id}">Update</a>
                    <a href="/delete?id=${user1.id}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</form>

</body>
</html>
