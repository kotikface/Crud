<%--
  Created by IntelliJ IDEA.
  User: SmartBook
  Date: 17.05.2020
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User INFO</title>
</head>
<body>
<%=session.getAttribute("user").toString()%>
<a href="/logout">Logout</a>
</body>
</html>
