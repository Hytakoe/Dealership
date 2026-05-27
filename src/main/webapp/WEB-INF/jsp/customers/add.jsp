<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить покупателя</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Добавить покупателя</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
        </nav>
    </header>

    <form id="addCustomerForm" action="${pageContext.request.contextPath}/customers" method="post">
        <input type="hidden" name="action" value="add">

        <div class="form-card">
            <div class="form-group">
                <label>ФИО покупателя:</label>
                <input type="text" name="fullName" required>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Возраст:</label>
                    <input type="number" name="age" min="18" max="120" required>
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
                <input type="tel" name="phone" required>
            </div>

            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email">
            </div>

            <div class="form-group">
                <label>Адрес:</label>
                <textarea name="address" rows="3"></textarea>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <a href="${pageContext.request.contextPath}/customers" class="btn">Отмена</a>
        </div>
    </form>
</div>
</body>
</html>