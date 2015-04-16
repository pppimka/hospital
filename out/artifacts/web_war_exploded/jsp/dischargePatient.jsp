<%@ taglib prefix="my" uri="/WEB-INF/my_tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ann_
  Date: 25.02.15
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename='messages'/>
<html>
<head>
    <title></title>
</head>
<body>
<fmt:message key='doctor'/><br/>
<c:out value="${staff_info.name}"/>
<c:out value="${staff_info.surname}"/>
<form action="" method="post">
    <c:forEach items="${healthy_patient_list}" var="wrapper"><br/>
        <table>
            <tr>
                <td><fmt:message key='name'/></td>
                <td><fmt:message key='surname'/></td>
            </tr>
            <tr>
                <td>${wrapper.value.patientInfoEntity.name}</td>
                <td>${wrapper.value.patientInfoEntity.surname}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <my:commandbutton info="${wrapper.key}" command="dischargePatient">
                        <fmt:message key='discharge'/>
                    </my:commandbutton>
                </td>
            </tr>
        </table>
    </c:forEach>
</form>
<my:commandbutton command="goToSetDiagnosis">
    <fmt:message key='setDiagnosis'/>
</my:commandbutton>
<my:commandbutton command="goToMainPage">
    <fmt:message key='goToMainPage'/>
</my:commandbutton>
</body>
</html>
