<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>История покупок</title>
</head>
<body>
<div class="container">
    <header>
        <h1>История покупок: ${customer.fullName}</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty sales}">
            <div class="empty-message">У этого покупателя нет покупок</div>
        </c:when>
        <c:otherwise>
            <table class="data-table">
                <thead>
                <tr>
                    <th>№ продажи</th>
                    <th>Автомобиль</th>
                    <th>Дата</th>
                    <th>Цена</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sales}" var="s">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.vehicle.brand} ${s.vehicle.model} (${s.vehicle.year})</td>
                        <td>${s.formattedSaleDate}</td>
                        <td>${s.salePrice} руб.</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/customers/${customer.id}" class="btn">Назад к покупателю</a>
    </div>
</div>
</body>
</html>