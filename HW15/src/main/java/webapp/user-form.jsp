<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Форма пользователя</title></head>
<body>
<h2><%= request.getAttribute("user") == null ||
        ((com.demo.model.User)request.getAttribute("user")).getId()==0 ?
        "Новый" : "Редактировать" %> пользователь</h2>
<form method="post" action="users">
    <input type="hidden" name="id" value="${user.id}">
    Логин:   <input name="login"  value="${user.login}"><br>
    Пароль:  <input name="password" value="${user.password}"><br>
    E-mail:  <input name="email" value="${user.email}"><br><br>
    <input type="submit" value="Сохранить">
</form>
<a href="users">← Назад</a>
</body>
</html>