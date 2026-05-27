<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Карточка покупателя</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Карточка покупателя</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
        </nav>
    </header>

    <div class="customer-card">
        <h2>${customer.fullName}</h2>
        <div class="customer-info">
            <div class="info-row">
                <span class="info-label">ID:</span>
                <span class="info-value">${customer.id}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Возраст:</span>
                <span class="info-value">${customer.age} лет</span>
            </div>
            <div class="info-row">
                <span class="info-label">Пол:</span>
                <span class="info-value">${customer.gender}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Телефон:</span>
                <span class="info-value">${customer.phone}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Email:</span>
                <span class="info-value">${customer.email}</span>
            </div>
            <div class="info-row">
                <span class="info-label">Адрес:</span>
                <span class="info-value">${customer.address}</span>
            </div>
        </div>

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/customers/edit?id=${customer.id}" class="btn btn-primary">Редактировать</a>
            <a href="${pageContext.request.contextPath}/sales/by-customer?id=${customer.id}" class="btn btn-info">История покупок</a>
            <a href="${pageContext.request.contextPath}/customers" class="btn">Назад</a>
        </div>
    </div>
</div>
</body>
</html>