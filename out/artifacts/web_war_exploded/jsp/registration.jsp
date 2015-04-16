<%--
  Created by IntelliJ IDEA.
  User: ann_
  Date: 02.02.15
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename='messages'/>
<html>
<head>
    <title><fmt:message key='registration'/></title>
</head>
<body>
<fmt:message key='greetingRegistration'/>
<form action="${pageContext.request.contextPath}/Controller" method="POST">
    <input type="hidden" name="command" value="newPatient">
    <fmt:message key='eMail'/>:<input type="email" name="eMail"><br>
    <fmt:message key='name'/>:<input type="text" name="firstName"><br>
    <fmt:message key='surname'/>:<input type="text" name="lastName"><br>
    <fmt:message key='birthday'/>:<input type="date" name="birthday"><br>
    <fmt:message key='phoneNumber'/><input type="text" name="phoneNumber"><br>
    <fmt:message key='password'/>:<input type="password" name="password"><br>
    <fmt:message key='checkPassword'/>:<input type="password" name="checkPassword"><br>
    <input type="submit" name="registration" value="<fmt:message key='registration'/>">
</form>
</body>
</html>
