<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Покупатели</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Покупатели</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <a href="${pageContext.request.contextPath}/vehicles">Автомобили</a>
            <a href="${pageContext.request.contextPath}/customers">Покупатели</a>
            <a href="${pageContext.request.contextPath}/sales">Продажи</a>
        </nav>
    </header>

    <c:choose>
        <c:when test="${empty customers}">
            <div class="empty-message">Нет зарегистрированных покупателей</div>
        </c:when>
        <c:otherwise>
            <table class="data-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>ФИО</th>
                    <th>Возраст</th>
                    <th>Пол</th>
                    <th>Телефон</th>
                    <th>Email</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customers}" var="c">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.fullName}</td>
                        <td>${c.age}</td>
                        <td>${c.gender}</td>
                        <td>${c.phone}</td>
                        <td>${c.email}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/customers/edit?id=${c.id}" class="btn-small">[ред]</a>
                            <a href="${pageContext.request.contextPath}/customers/${c.id}" class="btn-small">[просмотр]</a>
                            <a href="${pageContext.request.contextPath}/sales/by-customer?id=${c.id}" class="btn-small">[история]</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/customers/add" class="btn btn-primary">Добавить покупателя</a>
        <a href="${pageContext.request.contextPath}/" class="btn">На главную</a>
    </div>
</div>
</body>
</html>