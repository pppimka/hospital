<%--
  Created by IntelliJ IDEA.
  User: ann_
  Date: 05.02.15
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename='messages'/>
<html>
<head>
    <title></title>
</head>
<body>
<fmt:message key='greetingAuthorization'/>
<form action="${pageContext.request.contextPath}/Controller" method="POST">
    <input type="hidden" name="command" value="authorization">
    <fmt:message key='eMail'/>:<input type="email" name="eMail"><br>
    <fmt:message key='password'/>:<input type="password" name="password"><br>
    <input type="submit" name="authorization" value= "<fmt:message key='login' />" >
</form>
<form action="/Controller" method="POST">
    <input type="radio" name="lang" value="<fmt:message key='ukrainian'/>"/>
    <input type="radio" name="lang" value="<fmt:message key='english'/>"/>
    <input type="hidden" name="command" value="changeLanguage">
    <input type="submit" name="changeLanguage" value="<fmt:message key='changeLanguage'/>">
</form>
</body>
</html>