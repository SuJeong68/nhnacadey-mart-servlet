<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Login</title>

    <fmt:setLocale value="${initParam.get('lang')}"/>
    <fmt:setBundle basename="message" var="message"/>
</head>
<body>
    <h3>Hi, ${pageContext.getSession().getAttribute('id')}!!</h3><br/>
    <a href='/'><fmt:message key="main" bundle="${message}"/></a><br/>
    <a href='/logout.do'><fmt:message key="logout" bundle="${message}"/></a>
</body>
</html>
