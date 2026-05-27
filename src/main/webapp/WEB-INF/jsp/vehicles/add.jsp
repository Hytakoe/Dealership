<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить автомобиль</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Добавить автомобиль</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
        </nav>
    </header>

    <form id="addVehicleForm" action="${pageContext.request.contextPath}/vehicles" method="post">
        <input type="hidden" name="action" value="add">

        <div class="form-group">
            <label>Тип автомобиля:</label>
            <select name="vehicleType" id="vehicleType" required>
                <option value="CAR">Легковой</option>
                <option value="TRUCK">Грузовой</option>
            </select>
        </div>

        <div class="form-card">
            <h3>Основные данные</h3>
            <div class="form-group">
                <label>Марка:</label>
                <input type="text" name="brand" required>
            </div>

            <div class="form-group">
                <label>Модель:</label>
                <input type="text" name="model" required>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Год выпуска:</label>
                    <input type="number" name="year" min="1900" max="2026" required>
                </div>

                <div class="form-group">
                    <label>Цена (руб):</label>
                    <input type="number" name="price" step="0.01" required>
                </div>

                <div class="form-group">
                    <label>Цвет:</label>
                    <input type="text" name="color" required>
                </div>
            </div>
        </div>

        <div id="car-fields" class="form-card vehicle-type-fields">
            <h3>Данные легкового автомобиля</h3>
            <div class="form-group">
                <label>Тип кузова:</label>
                <select name="bodyType">
                    <c:forEach items="${bodyTypes}" var="type">
                        <option value="${type}">${type.displayName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Количество дверей:</label>
                    <input type="number" name="doorCount" min="2" max="5">
                </div>

                <div class="form-group">
                    <label>Количество мест:</label>
                    <input type="number" name="passengerCapacity" min="2" max="9">
                </div>
            </div>

            <div class="form-group">
                <label>Коробка передач:</label>
                <select name="transmission">
                    <option value="механика">Механика</option>
                    <option value="автомат">Автомат</option>
                    <option value="робот">Робот</option>
                    <option value="вариатор">Вариатор</option>
                </select>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Объем двигателя (л):</label>
                    <input type="number" name="engineVolume" step="0.1">
                </div>

                <div class="form-group">
                    <label>Тип топлива:</label>
                    <select name="fuelType">
                        <option value="бензин">Бензин</option>
                        <option value="дизель">Дизель</option>
                        <option value="электро">Электро</option>
                        <option value="гибрид">Гибрид</option>
                    </select>
                </div>
            </div>
        </div>

        <div id="truck-fields" class="form-card vehicle-type-fields" style="display: none;">
            <h3>Данные грузового автомобиля</h3>
            <div class="form-group">
                <label>Тип грузовика:</label>
                <select name="truckType">
                    <c:forEach items="${truckTypes}" var="type">
                        <option value="${type}">${type.displayName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Грузоподъемность (т):</label>
                    <input type="number" name="loadCapacity" step="0.1">
                </div>

                <div class="form-group">
                    <label>Количество осей:</label>
                    <input type="number" name="axleCount" min="2" max="5">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Объем кузова (м³):</label>
                    <input type="number" name="cargoVolume" step="0.1">
                </div>

                <div class="form-group">
                    <label>Материал кузова:</label>
                    <input type="text" name="bodyMaterial">
                </div>
            </div>

            <div class="form-group">
                <label>
                    <input type="checkbox" name="hasTrailerHitch" value="true">
                    Наличие фаркопа
                </label>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <a href="${pageContext.request.contextPath}/vehicles" class="btn">Отмена</a>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>