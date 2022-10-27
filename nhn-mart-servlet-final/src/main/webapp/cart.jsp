<%@ page import="com.nhnacademy.object.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Cart</title>

    <fmt:setLocale value="${pageContext.getServletContext().getAttribute('lang')}"/>
    <fmt:setBundle basename="message" var="message"/>
</head>
<body>
    <ol>
        <%  BigDecimal sum = new BigDecimal(0);
            List<Food> basket = (List<Food>) request.getServletContext().getAttribute("basket");
                for (Food food: basket) {
                    BigDecimal price = new BigDecimal(food.getPrice());
                    sum = sum.add(price);%>
        <li><%=food%></li>
        <%  }
            request.getSession().setAttribute("sum", sum);
        %>
    </ol>
    <p><fmt:message key="sum" bundle="${message}"/>: <%=sum%></p>
    <form method='post' action='/pay.do'>
        <input type='submit' value='<fmt:message key="pay" bundle="${message}"/>'/>
    </form><br/>
    <a href='/foods.do'><fmt:message key="foods" bundle="${message}"/></a>
</body>
</html>
