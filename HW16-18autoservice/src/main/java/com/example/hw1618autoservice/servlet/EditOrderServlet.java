package com.example.hw1618autoservice.servlet;


import com.example.hw1618autoservice.model.ServiceOrder;
import com.example.hw1618autoservice.repository.OrderRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-order")
public class EditOrderServlet extends HttpServlet {
    private final OrderRepository orderRepository = OrderRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        ServiceOrder order = orderRepository.findById(id);
        if (order != null) {
            req.setAttribute("order", order);
            req.getRequestDispatcher("/edit-order.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/orders");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter("id"));
        String customerName = req.getParameter("customerName");
        String carModel = req.getParameter("carModel");
        String description = req.getParameter("description");

        ServiceOrder order = new ServiceOrder(id, customerName, carModel, description);
        orderRepository.update(order);
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}