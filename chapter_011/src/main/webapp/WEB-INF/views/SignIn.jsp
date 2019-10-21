<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20.10.2019
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
    <%--@elvariable id="error" type="java"--%>
    <c:if test="${error != ''}">
        <div style="color: red">
            <c:out value="${error}"></c:out>
        </div>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/signIn" method="post">
        <label>Login:   <input type="text" name="login"></label><br>
        <label>Password:<input type="password" name="password"></label><br>
        <button type="submit" class="btn btn-default">Enter</button>
    </form>
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <button type="submit" class="btn btn-default">Register</button>
    </form>
</body>
</html>
