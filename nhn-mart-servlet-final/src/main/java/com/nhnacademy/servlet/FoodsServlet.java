package com.nhnacademy.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "foodsServlet", urlPatterns = "/foods")
public class FoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/foods.jsp");
        requestDispatcher.forward(req, resp);
    }
}
