<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Карточка автомобиля</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Карточка автомобиля</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
        </nav>
    </header>

    <div class="vehicle-card">
        <div class="vehicle-header">
            <h2>${vehicle.brand} ${vehicle.model}</h2>
            <span class="status ${vehicle.sold ? 'status-sold' : 'status-available'}">
                ${vehicle.sold ? 'Продано' : 'В наличии'}
            </span>
        </div>

        <div class="vehicle-info">
            <div class="info-row">
                <span class="info-label">Год выпуска:</span>
                <span class="info-value">${vehicle.year}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Цвет:</span>
                <span class="info-value">${vehicle.color}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Цена:</span>
                <span class="info-value">${vehicle.price} руб.</span>
            </div>
            <div class="info-row">
                <span class="info-label">Тип:</span>
                <span class="info-value">${vehicle.vehicleType}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Характеристики:</span>
                <span class="info-value">${vehicle.specificInfo}</span>
            </div>
        </div>

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/vehicles/edit?id=${vehicle.id}" class="btn btn-primary">Редактировать</a>
            <a href="${pageContext.request.contextPath}/vehicles" class="btn">Назад</a>
        </div>
    </div>
</div>
</body>
</html>