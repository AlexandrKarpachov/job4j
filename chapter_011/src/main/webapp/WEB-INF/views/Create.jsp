<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.10.2019
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta charset=\"UTF-8\">
        <title>Title</title>
    </head>
    <body>
        <form action="${pageContext.servletContext.contextPath}/create"
              method="post" enctype="multipart/form-data">
            <div class="checkbox">
                <label>Name:<input type="text" name="name" value=""/></label><br>
                <label>Login:<input type="text" name="login" value=""/></label><br>
                <label>Email:<input type="text" name="email" value=""/></label><br>
                <label>Password:<input type="password" name="password" value=""/></label><br>
                <input type="file" name="file">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>

    </body>
</html>
