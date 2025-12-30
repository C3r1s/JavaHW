<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<h2>Все пользователи</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Email</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>
                <a href="editUser?id=${user.id}">Редактировать</a> |
                <a href="deleteUser?id=${user.id}" onclick="return confirm('Удалить пользователя?')">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br><a href="register">Добавить нового пользователя</a>
</body>
</html>