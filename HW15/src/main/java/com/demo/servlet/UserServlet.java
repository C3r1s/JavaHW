package com.demo.servlet;

import com.demo.dao.UserDAO;
import com.demo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new" -> {
                req.setAttribute("user", new User());
                req.getRequestDispatcher("/user-form.jsp").forward(req, resp);
            }
            case "edit" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("user", UserDAO.findById(id));
                req.getRequestDispatcher("/user-form.jsp").forward(req, resp);
            }
            case "delete" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                UserDAO.delete(id);
                resp.sendRedirect("users");
            }
            default -> { // list
                List<User> list = UserDAO.findAll();
                req.setAttribute("users", list);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");
        User u = new User();
        u.setLogin(req.getParameter("login"));
        u.setPassword(req.getParameter("password"));
        u.setEmail(req.getParameter("email"));

        if (idStr == null || idStr.isEmpty()) {
            UserDAO.insert(u);
        } else {
            u.setId(Integer.parseInt(idStr));
            UserDAO.update(u);
        }
        resp.sendRedirect("users");
    }
}