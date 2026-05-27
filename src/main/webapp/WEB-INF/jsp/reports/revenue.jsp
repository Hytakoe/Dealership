<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Финансовый отчет</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Финансовый отчет</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/reports">Отчеты</a>
        </nav>
    </header>

    <div class="report-stats">
        <div class="stat-card">
            <div class="stat-label">Всего продаж</div>
            <div class="stat-value">${salesCount}</div>
        </div>
        <div class="stat-card">
            <div class="stat-label">Общая выручка</div>
            <div class="stat-value">
                <fmt:formatNumber value="${totalRevenue}" type="currency" currencySymbol="₽"/>
            </div>
        </div>
        <div class="stat-card">
            <div class="stat-label">Средний чек</div>
            <div class="stat-value">
                <fmt:formatNumber value="${salesCount > 0 ? totalRevenue / salesCount : 0}" type="currency" currencySymbol="₽"/>
            </div>
        </div>
    </div>

    <div class="form-card">
        <h3>Выручка по месяцам</h3>
        <table class="data-table">
            <thead>
            <tr>
                <th>Месяц</th>
                <th>Выручка</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${revenueByMonth}">
                <tr>
                    <td>Месяц ${entry.key}</td>
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