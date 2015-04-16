<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="/WEB-INF/my_tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ann_
  Date: 24.02.15
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename='messages'/>
<html>
<head>
    <title><fmt:message key='setDiagnosis'/></title>
</head>
<body>
<fmt:message key='doctor'/><br/>
<c:out value="${staff_info.name}"/>
<c:out value="${staff_info.surname}"/>
<form action="${pageContext.request.contextPath}/Controller" method="post">
    <c:forEach items="${patient_list}" var="wrapper"><br/>
        <table>
            <tr>
                <td><fmt:message key='name'/></td>
                <td><fmt:message key='surname'/></td>
            </tr>
            <tr>
                <td>${wrapper.value.name}</td>
                <td>${wrapper.value.surname}</td>
            </tr>
        </table>
        <input type="radio" name="patient_id" value="${wrapper.value.idPatient}">${wrapper.value.idPatient}<br/>
    </c:forEach>

    <c:forEach items="${prescription_list}" var="wrapper"><br/>
        <table>
            <tr>
                <td><fmt:message key='drugs'/></td>
                <td><fmt:message key='procedure'/></td>
                <td><fmt:message key='operation'/></td>
            </tr>
            <tr>
                <td>${wrapper.value.drugs}</td>
                <td>${wrapper.value.procedure}</td>
                <td>${wrapper.value.operation}</td>
            </tr>
        </table>
        <input type="radio" name="prescription_id" value="${wrapper.value.idPrescription}">
    </c:forEach>

    <c:forEach items="${diagnosis_list}" var="wrapper"><br/>
        <table>
            <tr>
                <td><fmt:message key='nameDiagnosis'/></td>
                <td><fmt:message key='description'/></td>
            </tr>
            <tr>
                <td>${wrapper.value.name}</td>
                <td>${wrapper.value.description}</td>
            </tr>
        </table>
        <input type="radio" name="diagnosis_id" value="${wrapper.value.idDiagnosis}">
    </c:forEach>
    <my:commandbutton command="setDiagnosis">
        <fmt:message key='setDiagnosis'/>
    </my:commandbutton>
    <my:commandbutton command="goToMainPage">
        <fmt:message key='goToMainPage'/>
    </my:commandbutton>
</form>

</body>
</html>
