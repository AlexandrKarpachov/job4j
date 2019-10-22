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
</head>
    <body>
        <h2>Edit</h2>
        <p>select role:</p>
        <form action="${pageContext.servletContext.contextPath}/edit" method='post'>
            <input type='hidden' name="id" value="<c:out value="${user.id}"></c:out>"/>
            <label>
                <select name="role">
                    <c:forEach items="${roles}" var="role">
                        <option value="<c:out value="${role}"></c:out>"><c:out value="${role}"></c:out></option>
                    </c:forEach>
                </select>
            </label>
            <br><br>
            <input type='hidden' name="login" value="<c:out value="${user.login}"></c:out>"/>
            <label>
                <input type='text' name="name" value="<c:out value="${user.name}"></c:out>"/>
            </label>Name<br>
            <label>
                <input type='text' name="email" value="<c:out value="${user.email}"></c:out>"/>
            </label>Email<br>
            <input type='submit' value='Update'>
        </form>
    </body>
</html>




