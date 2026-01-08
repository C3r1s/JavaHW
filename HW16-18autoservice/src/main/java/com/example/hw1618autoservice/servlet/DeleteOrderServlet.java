package com.example.hw1618autoservice.servlet;

import com.example.hw1618autoservice.repository.OrderRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-order")
public class DeleteOrderServlet extends HttpServlet {
    private final OrderRepository orderRepository = OrderRepository.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        orderRepository.delete(id);
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}