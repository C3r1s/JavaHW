package com.example.hw1618autoservice.servlet;

import com.example.hw1618autoservice.model.ServiceOrder;
import com.example.hw1618autoservice.repository.OrderRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-order")
public class AddOrderServlet extends HttpServlet {
    private final OrderRepository orderRepository = OrderRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add-order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String customerName = req.getParameter("customerName");
        String carModel = req.getParameter("carModel");
        String description = req.getParameter("description");

        ServiceOrder newOrder = new ServiceOrder(0, customerName, carModel, description);
        orderRepository.add(newOrder);

        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}