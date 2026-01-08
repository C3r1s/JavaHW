<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Автосервис "Надежда"</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Добро пожаловать в автосервис "Надежда"</h1>
    <p class="text-center">Мы готовы помочь вашему автомобилю!</p>
    <div class="d-grid gap-2 col-6 mx-auto">
        <a href="${pageContext.request.contextPath}/orders" class="btn btn-primary">Посмотреть все заказы</a>
        <a href="${pageContext.request.contextPath}/add-order" class="btn btn-success">Добавить новый заказ</a>
    </div>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
