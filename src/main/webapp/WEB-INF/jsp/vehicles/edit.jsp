<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактировать автомобиль</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Редактировать автомобиль</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
        </nav>
    </header>

    <form action="${pageContext.request.contextPath}/vehicles" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${vehicle.id}">

        <div class="form-card">
            <h3>Основные данные</h3>
            <div class="form-group">
                <label>Марка:</label>
                <input type="text" name="brand" value="${vehicle.brand}">
            </div>

            <div class="form-group">
                <label>Модель:</label>
                <input type="text" name="model" value="${vehicle.model}">
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Год выпуска:</label>
                    <input type="number" name="year" value="${vehicle.year}" min="1900" max="2026">
                </div>

                <div class="form-group">
                    <label>Цена (руб):</label>
                    <input type="number" name="price" step="0.01" value="${vehicle.price}">
                </div>

                <div class="form-group">
                    <label>Цвет:</label>
                    <input type="text" name="color" value="${vehicle.color}">
                </div>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Сохранить изменения</button>
            <a href="${pageContext.request.contextPath}/vehicles" class="btn">Отмена</a>
        </div>
    </form>

    <c:if test="${not vehicle.sold}">
        <div class="delete-section">
            <form action="${pageContext.request.contextPath}/vehicles" method="post" onsubmit="return confirm('Вы уверены?')">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="${vehicle.id}">
                <button type="submit" class="btn btn-danger">Удалить автомобиль</button>
            </form>
        </div>
    </c:if>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>