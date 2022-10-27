<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>ChangeLang</title>

    <fmt:setLocale value="${pageContext.getServletContext().getAttribute('lang')}"/>
    <fmt:setBundle basename="message" var="message"/>
</head>
<body>
    <form method="post" action="/changeLang.do">
        <fmt:message key="changeLang" bundle="${message}"/><br/>
        <input type="radio" name="lang" value="ko"><label>Korean</label>
        <input type="radio" name="lang" value="en"><label>English</label>
        <input type="submit"/>
    </form>
</body>
</html>
