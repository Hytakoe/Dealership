<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>История продаж автомобиля</title>
</head>
<body>
<div class="container">
    <header>
        <h1>История продаж: ${vehicle.brand} ${vehicle.model}</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty sales}">
            <div class="empty-message">Этот автомобиль еще не продавался</div>
        </c:when>
        <c:otherwise>
            <table class="data-table">
                <thead>
                <tr>
                    <th>№ продажи</th>
                    <th>Покупатель</th>
                    <th>Дата</th>
                    <th>Цена</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sales}" var="s">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.customer.fullName}</td>
                        <td>${s.formattedSaleDate}</td>
                        <td>${s.salePrice} руб.</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/vehicles/${vehicle.id}" class="btn">Назад к автомобилю</a>
    </div>
</div>
</body>
</html>