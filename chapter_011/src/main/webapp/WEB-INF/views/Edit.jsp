<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type="com.sun.org.apache.xml.internal.security.signature.Manifest"--%>
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
    <title>Edit</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
</head>
    <body>

        <div class="container">
            <h1 class="col-sm-offset-4">Edit</h1>
            <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit" method='post'>
                <div class="form-group" id="name-group">
                    <label class="control-label col-sm-2" for="name">Name:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="name"
                               value="<c:out value="${user.name}"></c:out>">
                    </div>
                </div>
                <div class="form-group" id="email-group">
                    <label class="control-label col-sm-2" for="email">Email:</label>
                    <div class="col-sm-5">
                        <input type="email" class="form-control" name="email" id="email"
                               value="<c:out value="${user.email}"></c:out>">
                    </div>
                </div>
                <div class="form-group" id="pass-group">
                    <label class="control-label col-sm-2" for="pwd">Password:</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="password"
                               value="<c:out value="${user.password}"></c:out>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Country:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="country"
                               value="<c:out value="${user.country}"></c:out>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="cities">City:</label>
                    <div class="col-sm-5">
                        <select class="form-control" id="cities" name="city"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="role">Select role:</label>
                    <div class="col-sm-5">
                        <select class="form-control" name="role">
                            <c:forEach items="${roles}" var="role">
                                <option value="<c:out value="${role}"></c:out>"><c:out value="${role}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <button  type='submit' class="btn btn-success col-sm-offset-4">Update</button>
                </div>
            </form>
        </div>
        <input type='hidden' name="id" value="<c:out value="${user.id}"></c:out>"/>
        <input type='hidden' name="login" value="<c:out value="${user.login}"></c:out>"/>
    </body>
</html>




