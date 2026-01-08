<%@ page import="com.example.hw1618autoservice.model.ServiceOrder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать заказ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Редактирование заказа</h1>
    <% ServiceOrder order = (ServiceOrder) request.getAttribute("order"); %>
    <form action="${pageContext.request.contextPath}/edit-order" method="post">
        <input type="hidden" name="id" value="<%= order.getId() %>">
        <div class="mb-3">
            <label for="customerName" class="form-label">Имя клиента:</label>
            <input type="text" class="form-control" id="customerName" name="customerName" value="<%= order.getCustomerName() %>" required>
        </div>
        <div class="mb-3">
            <label for="carModel" class="form-label">Модель авто:</label>
            <input type="text" class="form-control" id="carModel" name="carModel" value="<%= order.getCarModel() %>" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Описание работ:</label>
            <textarea class="form-control" id="description" name="description" rows="4" required><%= order.getServiceDescription() %></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Сохранить изменения</button>
        <a href="${pageContext.request.contextPath}/orders" class="btn btn-secondary">Отмена</a>
    </form>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>