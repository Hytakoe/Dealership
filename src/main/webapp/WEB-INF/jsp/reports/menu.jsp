<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Отчеты</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Отчеты и статистика</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
            <a href="${pageContext.request.contextPath}/reports">Отчеты</a>
        </nav>
    </header>

    <div class="reports-menu">
        <a href="${pageContext.request.contextPath}/reports/revenue" class="report-card">
            <h3>Финансовый отчет</h3>
            <p>Выручка по месяцам и общая статистика</p>
        </a>

        <a href="${pageContext.request.contextPath}/reports/popular" class="report-card">
            <div class="report-icon">[График</div>
            <h3>Популярные автомобили</h3>
            <p>Самые продаваемые марки и модели</p>
        </a>

        <a href="${pageContext.request.contextPath}/reports/top-customers" class="report-card">
            <div class="report-icon">[Звезда</div>
            <h3>Лучшие покупатели</h3>
            <p>Топ покупателей по сумме покупок</p>
        </a>
    </div>
</div>
</body>
</html>