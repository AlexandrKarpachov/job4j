
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <style><%@include file="/WEB-INF/views/css/style.css"%></style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script charset="UTF-8" type="text/javascript"> <%@include file="/WEB-INF/views/js/scripts.js" %> </script>

    <%--@elvariable id="error" type="java"--%>
    <%--@elvariable id="login" type="java"--%>

</head>
<style>

</style>
<body>
    <div class="container">
        <h1 class="col-sm-offset-2">Authorise:</h1>
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/signIn" method="post"
              onsubmit="return authFormValidate()">
            <div class="form-group" id="login-group">
                <label class="control-label col-sm-2" for="login">Login:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="login" id="login" placeholder="login"
                           onclick="hideAlert('emptyLoginAlert', 'login-group')">
                </div>
                <div class="col-sm-3 alert alert-danger hidden" id="emptyLoginAlert">
                    <span id="login-alert-msg">Please fill in field</span>
                </div>
            </div>
            <div class="form-group" id="pass-group">
                <label class="control-label col-sm-2" for="pwd">Password:</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" name="password" placeholder="Enter password"
                           id="pwd" onclick="hideAlert('emptyPassAlert', 'pass-group')">
                </div>
                <div class="col-sm-3 alert alert-danger hidden" id="emptyPassAlert">
                    <span id="pass-alert-msg">Please fill in field</span>
                </div>
            </div>
            <div class="btn-group col-sm-offset-3">
                <button type="submit" class="btn btn-success" >Enter</button>
                <button type="submit" form="create" class="btn btn-success">Register</button>
            </div>
        </form>
        <form action="${pageContext.servletContext.contextPath}/create" method="get" id="create"></form>
    </div>
</body>
</html>
