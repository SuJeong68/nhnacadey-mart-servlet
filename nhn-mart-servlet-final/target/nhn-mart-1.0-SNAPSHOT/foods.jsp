<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Foods</title>

    <fmt:setLocale value="${pageContext.getServletContext().getAttribute('lang')}"/>
    <fmt:setBundle basename="message" var="message"/>
</head>
<body>
    <form method='post' action='/cart.do'>
        <p><fmt:message key="onion" bundle="${message}"/><input type='text' name='food'/></p>
        <p><fmt:message key="egg" bundle="${message}"/><input type='text' name='food'/></p>
        <p><fmt:message key="greenOnion" bundle="${message}"/><input type='text' name='food'/></p>
        <p><fmt:message key="apple" bundle="${message}"/><input type='text' name='food'/></p>
        <input type='submit' name='장바구니에 담기'/>
    </form>
</body>
</html>
