<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <title>Index</title>

    <fmt:setLocale value="${pageContext.getServletContext().getAttribute('lang')}"/>
    <fmt:setBundle basename="message" var="message"/>
  </head>
  <body>
    <a href="/init.do"><fmt:message key="init" bundle="${message}"/></a><br/>
    <a href="/foods.do"><fmt:message key="foods" bundle="${message}"/></a><br/>
    <a href="/cart.do"><fmt:message key="cart" bundle="${message}"/></a><br/>
    <a href="/changeLang.do"><fmt:message key="language" bundle="${message}"/></a>: ${pageContext.getServletContext().getAttribute('lang')}<br/>
    <p><fmt:message key="balance" bundle="${message}"/>: <%=config.getServletContext().getAttribute(request.getSession().getAttribute("id") + "balance")%></p>
  </body>
</html>

