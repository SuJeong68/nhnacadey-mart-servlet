<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <title>Init</title>

        <fmt:setLocale value="${pageContext.getServletContext().getAttribute('lang')}"/>
        <fmt:setBundle basename="message" var="message"/>
    </head>
    <body>
        <p><fmt:message key="initMsg" bundle="${message}"/></p>
        <a href="/foods.do"><fmt:message key="foods" bundle="${message}"/></a>
    </body>
</html>

