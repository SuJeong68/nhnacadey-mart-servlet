<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Pay</title>

    <fmt:setLocale value="${pageContext.getServletContext().getAttribute('lang')}"/>
    <fmt:setBundle basename="message" var="message"/>
</head>
<body>
    <%  BigDecimal sum = (BigDecimal) request.getSession().getAttribute("sum");
        BigDecimal balance = (BigDecimal) request.getServletContext().getAttribute(request.getSession().getAttribute("id") + "balance");

        if (balance.subtract(sum).compareTo(new BigDecimal(0)) == -1) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
        balance = balance.subtract(sum);
        request.getServletContext().setAttribute(request.getSession().getAttribute("id") + "balance", balance);
    %>
    <p><fmt:message key="totalPaid" bundle="${message}"/>: <%=sum%></p>
    <p><fmt:message key="balanceAfterPay" bundle="${message}"/>: <%=balance%></p>
    <a href='/'><fmt:message key="main" bundle="${message}"/></a>
    <%  request.getServletContext().setAttribute("basket",  new ArrayList<>()); %>
</body>
</html>
