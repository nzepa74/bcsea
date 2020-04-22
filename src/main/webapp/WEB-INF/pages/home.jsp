
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="auth" property="principal"/>

<html>
<head>
    <title>BCSEA</title>
    <meta name="decorator" content="/layout/close-layout.jsp"/>
</head>
<body>

<security:authorize access="hasRole('0101-01-VIEW')">
    <jsp:include page="bcsea/operatorsTaskList.jsp"/>
</security:authorize>

<security:authorize access="hasRole('0101-02-VIEW')">
    <jsp:include page="bcsea/paymentDetail.jsp"/>
</security:authorize>

<security:authorize access="hasRole('0101-03-VIEW')">
    <jsp:include page="bcsea/serviceActivityDuration.jsp"/>
</security:authorize>

</body>
</html>
