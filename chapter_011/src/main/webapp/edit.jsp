<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.10.2019
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <%int id = Integer.parseInt(request.getParameter("id"));
        User user = ValidateService.getInstance().findById(new User(id, null, null, null));%>
        <form action="<%=request.getContextPath()%>/edit" method='post'>
            <input type='hidden' name="id" value="<%=user.getId()%>"/><br>
            <input type='hidden' name="login" value="<%=user.getLogin()%>"/><br>
            <input type='text' name="name" value="<%=user.getName()%>"/>Name<br>
            <input type='text' name="email" value="<%=user.getEmail()%>"/>Email<br>
            <input type="text" name="createDate" value="<%=user.getCreateDate()%>"/>Additional Information<br>
            <input type='submit' value='Update'>
        </form>
    </body>
</html>




