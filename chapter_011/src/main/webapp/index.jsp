<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %><%--
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
            <th>Login</th><th>Name</th><th>Email</th>
        </tr>
        <% for (User user : ValidateService.getInstance().findAll()) { %>
            <tr>
                <td><%=user.getLogin()%></td>
                <td><%=user.getName() %></td>
                <td><%=user.getEmail()%></td>
                <td>
                    <form action="<%=request.getContextPath()%>/edit.jsp" method="get">
                    <input type='hidden' name='id' value="<%=user.getId()%>">
                    <input type='submit' value='Edit'>
                    </form>
                </td>
                <td>
                    <form action="<%=request.getContextPath()%>/users" method="post">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        <% } %>
        </table>
        <form action="<%=request.getContextPath()%>/create" method='get'>
        <input type="submit" value="Add new User">
        </form>
    </body>
</html>
