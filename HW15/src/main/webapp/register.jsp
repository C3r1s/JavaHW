<!-- webapp/register.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация нового пользователя</h2>
<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
<form method="POST" action="register">
    Имя пользователя: <input type="text" name="username" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Пароль: <input type="password" name="password" required><br><br>
    <input type="submit" value="Зарегистрироваться">
</form>
<br><a href="listUsers">Посмотреть всех пользователей</a>
</body>
</html>