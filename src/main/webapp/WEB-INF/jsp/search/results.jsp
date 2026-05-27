<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результаты поиска</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Результаты поиска: "${searchQuery}"</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty searchResults}">
            <div class="empty-message">Ничего не найдено</div>
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
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${searchResults}" var="v">
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.brand}</td>
                        <td>${v.model}</td>
                        <td>${v.year}</td>
                        <td>${v.price} руб.</td>
                        <td class="${v.sold ? 'status-sold' : 'status-available'}">
                                ${v.sold ? 'Продано' : 'В наличии'}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/vehicles" class="btn">Все автомобили</a>
    </div>
</div>
</body>
</html>