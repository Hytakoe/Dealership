<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Лучшие покупатели</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Лучшие покупатели</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/reports">Отчеты</a>
        </nav>
    </header>

    <div class="form-card">
        <h3>Топ-10 покупателей по сумме покупок</h3>
        <table class="data-table">
            <thead>
            <tr>
                <th>Место</th>
                <th>Покупатель</th>
                <th>Количество покупок</th>
                <th>Общая сумма</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${topCustomers}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${entry.key}</td>
                    <td>${purchasesByCustomer[entry.key]}</td>
                    <td><fmt:formatNumber value="${entry.value}" type="currency" currencySymbol="₽"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/reports" class="btn">Назад к отчетам</a>
    </div>
</div>
</body>
</html>