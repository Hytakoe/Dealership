<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Автосалон - Главная</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Автосалон</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
            <a href="${pageContext.request.contextPath}/reports">Отчеты</a>
        </nav>
    </header>

    <div class="search-bar">
        <form action="${pageContext.request.contextPath}/search" method="get">
            <input type="text" name="q" placeholder="Поиск автомобилей по марке или модели..." />
            <button type="submit">Найти</button>
        </form>
    </div>

    <div class="dashboard">
        <div class="card">
            <div class="card-icon">Авто</div>
            <h3>Автомобили в наличии</h3>
            <div class="number">${availableCount}</div>
            <a href="${pageContext.request.contextPath}/vehicles" class="btn">Подробнее</a>
        </div>

        <div class="card">
            <h3>Продажи</h3>
            <div class="number">${salesCount}</div>
            <a href="${pageContext.request.contextPath}/sales" class="btn">Подробнее</a>
        </div>

        <div class="card">
            <h3>Покупатели</h3>
            <div class="number">${customersCount}</div>
            <a href="${pageContext.request.contextPath}/customers" class="btn">Подробнее</a>
        </div>
    </div>

    <div class="action-buttons">
        <a href="${pageContext.request.contextPath}/vehicles/add" class="btn btn-primary">Добавить автомобиль</a>
        <a href="${pageContext.request.contextPath}/sales/sell" class="btn btn-success">Продать автомобиль</a>
        <a href="${pageContext.request.contextPath}/customers/add" class="btn btn-info">Добавить покупателя</a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>