
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="servises.UserService" %>
<%@ page import="entity.User" %>
<%@ page import="exception.DBException" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: SmartBook
  Date: 07.05.2020
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form method="POST">
    <h2> Update user </h2>

    <input type="text" name="id" hidden  value='${requestScope.user.id}'/>
    <input type="text" name="email"   value='${requestScope.user.email}'/>
    <input type="text" name="password"   value='${requestScope.user.password}'/>
    <input type="text" name="age"  value='${requestScope.user.age}'/>
    <input type="text" name="role" value='${requestScope.user.role}'>
    <button type="submit" formaction="/update">UPDATE</button>
</form>

</body>
</html>