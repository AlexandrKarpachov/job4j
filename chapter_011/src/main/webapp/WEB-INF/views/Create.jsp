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
        <title>Create new User</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script charset="UTF-8" type="text/javascript"> <%@include file="/WEB-INF/views/js/scripts.js"%></script>
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
    </head>
    <script>
        $(
            $.ajax("./cities", {
                method: "get",
                complete : function(data) {
                    var cities_list = JSON.parse(data.responseText);
                    var result = "";
                    for (var i = 0; i < cities_list.length; i++) {
                        result += "<option>" + cities_list[i] + "</option>\n"
                    }
                    var cities_select = document.getElementById("cities");
                    cities_select.innerHTML = result;
                }
            })
        )
    </script>
    <body>
    <div class="container">
        <h1 class="col-sm-offset-2">Create new User:</h1>
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/create"
              method="post" enctype="multipart/form-data" onsubmit="return createFormValidate()">
            <div class="form-group" id="name-group">
                <label class="control-label col-sm-2" for="name">Name:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter Name"
                           onclick="hideAlert('emptyNameAlert', 'name-group')">
                </div>
                <div class="col-sm-3 alert alert-danger hidden" id="emptyNameAlert">
                    <span id="name-alert-msg"></span>
                </div>
            </div>
            <div class="form-group" id="login-group">
                <label class="control-label col-sm-2" for="login">Login:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="login" id="login" placeholder="login"
                           onclick="hideAlert('emptyLoginAlert', 'login-group')" >
                </div>
                <div class="col-sm-3 alert alert-danger hidden" id="emptyLoginAlert">
                    <span id="login-alert-msg"></span>
                </div>
            </div>
            <div class="form-group" id="email-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" name="email" id="email" placeholder="Enter Email"
                           onclick="hideAlert('emptyEmailAlert', 'email-group')">
                </div>
                <div class="col-sm-3 alert alert-danger hidden" id="emptyEmailAlert">
                    <span id="email-alert-msg"></span>
                </div>
            </div>
            <div class="form-group" id="pass-group">
                <label class="control-label col-sm-2" for="pwd">Password:</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" name="password" placeholder="Enter password"
                           id="pwd" onclick="hideAlert('emptyPassAlert', 'pass-group')">
                </div>
                <div class="col-sm-3 alert alert-danger hidden" id="emptyPassAlert">
                    <span id="pass-alert-msg"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="cities">City:</label>
                <div class="col-sm-5">
                    <select class="form-control" id="cities" name="city"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="btn btn-primary col-sm-offset-2" for="file-selector">
                    <input id="file-selector" type="file" name="file" style="display: none"
                           onchange="$('#upload-file-info').html(this.files[0].name)">
                    <span>Add photo</span>
                </label>
                <span class='label label-info' id="upload-file-info"></span>
            </div>

            <div class="form-group ">
                <button type="submit" class="btn btn-success col-sm-offset-4">Enter</button>
            </div>
        </form>
    </div>
    </body>
</html>
