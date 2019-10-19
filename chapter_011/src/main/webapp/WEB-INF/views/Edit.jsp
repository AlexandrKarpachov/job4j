<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.10.2019
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <form action="${pageContext.servletContext.contextPath}/edit" method='post'>
            <input type='hidden' name="id" value="<c:out value="${user.id}"></c:out>"/><br>
            <input type='hidden' name="login" value="<c:out value="${user.login}"></c:out>"/><br>
            <input type='text' name="name" value="<c:out value="${user.name}"></c:out>"/>Name<br>
            <input type='text' name="email" value="<c:out value="${user.email}"></c:out>"/>Email<br>
            <input type='submit' value='Update'>
        </form>
    </body>
</html>




