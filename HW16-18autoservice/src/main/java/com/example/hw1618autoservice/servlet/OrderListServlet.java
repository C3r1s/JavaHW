package com.example.hw1618autoservice.servlet;

import com.example.hw1618autoservice.model.ServiceOrder;
import com.example.hw1618autoservice.repository.OrderRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderListServlet extends HttpServlet {
    private final OrderRepository orderRepository = OrderRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ServiceOrder> orders = orderRepository.findAll();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/orders.jsp").forward(req, resp);
    }
}