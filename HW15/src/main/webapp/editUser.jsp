<!-- webapp/editUser.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Редактировать пользователя</title>
</head>
<body>
<h2>Редактировать пользователя</h2>
<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
<%
  com.example.User user = (com.example.User) request.getAttribute("user");
  if (user == null) {
    response.sendRedirect("listUsers");
    return;
  }
%>
<form method="POST" action="editUser">
  <input type="hidden" name="id" value="<%= user.getId() %>">
  Имя пользователя: <input type="text" name="username" value="<%= user.getUsername() %>" required><br><br>
  Email: <input type="email" name="email" value="<%= user.getEmail() %>" required><br><br>
  Пароль: <input type="password" name="password" value="<%= user.getPassword() %>" required><br><br>
  <input type="submit" value="Сохранить изменения">
</form>
<br><a href="listUsers">Назад к списку</a>
</body>
</html>