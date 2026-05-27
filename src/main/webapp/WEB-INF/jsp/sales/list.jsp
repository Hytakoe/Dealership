<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Продажи</title>
</head>
<body>
<div class="container">
    <header>
        <h1>История продаж</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty sales}">
            <div class="empty-message">Продаж не было</div>
        </c:when>
        <c:otherwise>
            <table class="data-table">
                <thead>
                <tr>
                    <th>N</th>
                    <th>Автомобиль</th>
                    <th>Покупатель</th>
                    <th>Дата продажи</th>
                    <th>Цена</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sales}" var="s">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.vehicle.brand} ${s.vehicle.model} (${s.vehicle.year})</td>
                        <td>${s.customer.fullName}</td>
                        <td>${s.formattedSaleDate}</td>
                        <td>${s.salePrice} руб.</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/vehicles/${s.vehicle.id}" class="btn-small">[авто]</a>
                            <a href="${pageContext.request.contextPath}/customers/${s.customer.id}" class="btn-small">[покупатель]</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/sales/sell" class="btn btn-success">Новая продажа</a>
        <a href="${pageContext.request.contextPath}/" class="btn">На главную</a>
    </div>
</div>
</body>
</html>