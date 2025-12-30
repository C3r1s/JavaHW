<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Пользователи</title></head>
<body>
<h2>Список пользователей</h2>
<a href="users?action=new">Добавить</a>
<table border="1" cellpadding="5">
    <tr><th>ID</th><th>Логин</th><th>Пароль</th><th>E-mail</th><th></th></tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.login}</td>
            <td>${u.password}</td>
            <td>${u.email}</td>
            <td>
                <a href="users?action=edit&id=${u.id}">Изменить</a> |
                <a href="users?action=delete&id=${u.id}"
                   onclick="return confirm('Удалить?')">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>