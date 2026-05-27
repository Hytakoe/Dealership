<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактировать покупателя</title>

</head>
<body>
<div class="container">
    <header>
        <h1>Редактировать покупателя</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
        </nav>
    </header>

    <form action="${pageContext.request.contextPath}/customers" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${customer.id}">

        <div class="form-card">
            <div class="form-group">
                <label>ФИО покупателя:</label>
                <input type="text" name="fullName" value="${customer.fullName}">
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Возраст:</label>
                    <input type="number" name="age" value="${customer.age}" min="18" max="120">
                </div>

                <div class="form-group">
                    <label>Пол:</label>
                    <select name="gender">
                        <option value="М" ${customer.gender == 'М' ? 'selected' : ''}>Мужской</option>
                        <option value="Ж" ${customer.gender == 'Ж' ? 'selected' : ''}>Женский</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label>Телефон:</label>
                <input type="tel" name="phone" value="${customer.phone}">
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${customer.email}">
            </div>

            <div class="form-group">
                <label>Адрес:</label>
                <textarea name="address" rows="3">${customer.address}</textarea>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Сохранить изменения</button>
            <a href="${pageContext.request.contextPath}/customers" class="btn">Отмена</a>
        </div>
    </form>
</div>
</body>
</html>