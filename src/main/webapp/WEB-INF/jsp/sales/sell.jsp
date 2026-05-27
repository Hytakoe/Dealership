<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Продажа автомобиля</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Продажа автомобиля</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
        </nav>
    </header>

    <form action="${pageContext.request.contextPath}/sales" method="post">
        <input type="hidden" name="action" value="sell">

        <div class="form-card">
            <h3>Выберите автомобиль</h3>
            <div class="form-group">
                <select name="vehicleId" required>
                    <option value="">-- Выберите автомобиль --</option>
                    <c:forEach items="${availableVehicles}" var="v">
                        <option value="${v.id}">${v.brand} ${v.model} (${v.year}) - ${v.price} руб.</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-card">
            <h3>Покупатель</h3>
            <div class="form-group">
                <label>
                    <input type="radio" name="customerOption" value="existing" checked> Выбрать существующего
                </label>
                <label>
                    <input type="radio" name="customerOption" value="new"> Новый покупатель
                </label>
            </div>

            <div id="existing-customer-fields">
                <div class="form-group">
                    <select name="customerId">
                        <option value="">-- Выберите покупателя --</option>
                        <c:forEach items="${customers}" var="c">
                            <option value="${c.id}">${c.fullName} (${c.phone})</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div id="new-customer-fields" style="display: none;">
                <div class="form-group">
                    <label>ФИО:</label>
                    <input type="text" name="fullName">
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Возраст:</label>
                        <input type="number" name="age" min="18" max="120">
                    </div>
                    <div class="form-group">
                        <label>Пол:</label>
                        <select name="gender">
                            <option value="М">Мужской</option>
                            <option value="Ж">Женский</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label>Телефон:</label>
                    <input type="tel" name="phone">
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email">
                </div>
                <div class="form-group">
                    <label>Адрес:</label>
                    <textarea name="address" rows="2"></textarea>
                </div>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-success">Оформить продажу</button>
            <a href="${pageContext.request.contextPath}/sales" class="btn">Отмена</a>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>