<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Популярные автомобили</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Популярные автомобили</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/reports">Отчеты</a>
        </nav>
    </header>

    <div class="form-card">
        <h3>Продажи по маркам</h3>
        <table class="data-table">
            <thead>
            <tr>
                <th>Марка</th>
                <th>Количество продаж</th>
                <th>Выручка</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${salesByBrand}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                    <td><fmt:formatNumber value="${revenueByBrand[entry.key]}" type="currency" currencySymbol="₽"/></td>
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