<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Автомобили - Автосалон</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Автомобили</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
            <a href="${pageContext.request.contextPath}/reports">Отчеты</a>
        </nav>
    </header>

    <div class="tabs">
        <button class="tab-btn active" data-tab="available">В наличии (${availableVehicles.size()})</button>
        <button class="tab-btn" data-tab="all">Все автомобили (${vehicles.size()})</button>
    </div>

    <div id="available" class="tab-content active">
        <c:choose>
            <c:when test="${empty availableVehicles}">
                <div class="empty-message">Нет автомобилей в наличии</div>
            </c:when>
            <c:otherwise>
                <table class="data-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Марка</th>
                        <th>Модель</th>
                        <th>Год</th>
                        <th>Цена</th>
                        <th>Тип</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${availableVehicles}" var="v">
                        <tr>
                            <td>${v.id}</td>
                            <td>${v.brand}</td>
                            <td>${v.model}</td>
                            <td>${v.year}</td>
                            <td>${v.price} руб.</td>
                            <td>${v.vehicleType}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/vehicles/edit?id=${v.id}" class="btn-small">[ред]</a>
                                <a href="${pageContext.request.contextPath}/vehicles/${v.id}" class="btn-small">[просмотр]</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <div id="all" class="tab-content">
        <c:choose>
            <c:when test="${empty vehicles}">
                <div class="empty-message">Нет автомобилей</div>
            </c:when>
            <c:otherwise>
                <table class="data-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Марка</th>
                        <th>Модель</th>
                        <th>Год</th>
                        <th>Цена</th>
                        <th>Статус</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${vehicles}" var="v">
                        <tr>
                            <td>${v.id}</td>
                            <td>${v.brand}</td>
                            <td>${v.model}</td>
                            <td>${v.year}</td>
                            <td>${v.price} руб.</td>
                            <td class="${v.sold ? 'status-sold' : 'status-available'}">
                                    ${v.sold ? 'Продано' : 'В наличии'}
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/vehicles/${v.id}" class="btn-small">[просмотр]</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/vehicles/add" class="btn btn-primary">Добавить автомобиль</a>
        <a href="${pageContext.request.contextPath}/" class="btn">На главную</a>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>