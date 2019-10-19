<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.10.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <body>
        <table border>
        <tr>
            <th>Login</th><th>Name</th><th>Email</th><th>Photo</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td>
                    <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                         alt="${pageContext.servletContext.contextPath}/${user.photoId}" width="100px" height="100px"/>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type='hidden' name='id' value="<c:out value="${user.id}"></c:out>">
                    <input type='submit' value='Edit'>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/users" method="post">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
        <form action="<%=request.getContextPath()%>/create" method='get'>
        <input type="submit" value="Add new User">
        </form>
    </body>
</html>
