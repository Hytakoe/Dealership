<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ошибка</title>
</head>
<body>
<div class="container">
    <div class="error-container">
        <h1>Произошла ошибка</h1>
        <div class="error-message">
            <p>${error != null ? error : "Неизвестная ошибка"}</p>
        </div>
        <a href="${pageContext.request.contextPath}/" class="btn">Вернуться на главную</a>
        <a href="javascript:history.back()" class="btn">Назад</a>
    </div>
</div>
</body>
</html>