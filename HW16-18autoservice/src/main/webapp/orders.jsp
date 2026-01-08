<%@ page import="java.util.List" %>
<%@ page import="com.example.hw1618autoservice.model.ServiceOrder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Список заказов</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Список текущих заказов</h1>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Имя клиента</th>
            <th>Модель авто</th>
            <th>Описание работ</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.customerName}</td>
                <td>${order.carModel}</td>
                <td>${order.serviceDescription}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/edit-order?id=${order.id}" class="btn btn-primary btn-sm">Редактировать</a>
                    <form action="${pageContext.request.contextPath}/delete-order" method="post" class="d-inline" onsubmit="return confirm('Вы уверены, что хотите удалить этот заказ?');">
                        <input type="hidden" name="id" value="${order.id}">
                        <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/add-order" class="btn btn-success">Добавить новый заказ</a>
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">На главную</a>
    </div>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>