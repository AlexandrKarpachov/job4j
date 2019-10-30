<%@ page import="ru.job4j.servlets.models.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="users" scope="request" type="java.util.List"/>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.10.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <c:set var="admin">
        <%=Role.ADMIN.name()%>
    </c:set>
    <c:set var="role">
        <%=(String) session.getAttribute("role")%>
    </c:set>


</head>

    <body>
        <div class="container-fluid">
            <div class="col-sm-offset-10">
                <form action="<%=request.getContextPath()%>/logout">
                    <button type="submit" class="btn btn-warning">Quit</button>
                </form>
            </div>
            <div class="container">
                <h2>Users</h2>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Login</th><th>Name</th><th>Email</th><th>City</th><th>Role</th><th>Photo</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td><c:out value="${user.login}"></c:out></td>
                            <td><c:out value="${user.name}"></c:out></td>
                            <td><c:out value="${user.email}"></c:out></td>
                            <td><c:out value="${user.city}"></c:out></td>
                            <td><c:out value="${user.role}"></c:out></td>
                            <td>
                                <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                                     alt="${pageContext.servletContext.contextPath}/${user.photoId}" width="100px" height="100px"/>
                            </td>
                            <td>
                                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                                    <input type='hidden' name='id' value="<c:out value="${user.id}"></c:out>">
                                    <button type='submit' class="btn btn-primary">Edit</button>
                                </form>
                            </td>
                            <c:if test="${role == 'ADMIN'}">
                                <td>
                                    <form action="${pageContext.servletContext.contextPath}/users" method="post">
                                        <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-sm-offset-5">
            <form action="<%=request.getContextPath()%>/create" method='get'>
                <button type="submit" class="btn btn-success">Add new User</button>

            </form>
        </div>
    </body>
</html>
