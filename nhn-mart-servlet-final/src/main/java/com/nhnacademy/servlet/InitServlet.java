package com.nhnacademy.servlet;

import com.nhnacademy.object.Food;
import com.nhnacademy.object.FoodStand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "initServlet", urlPatterns = "/init")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletConfig().getServletContext();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String[] strFoodlist = String.valueOf(servletContext.getInitParameter("foodlist")).split("\n");
        List<List<String>> foodlist = new ArrayList<>();
        for (String s: strFoodlist) {
            foodlist.add(Arrays.asList(s.split(", ")).stream().map(String::trim).collect(Collectors.toList()));
        }

        FoodStand foodStand = new FoodStand();
        for (List list: foodlist) {
            Food food = new Food(list.get(0).toString(), Integer.parseInt(list.get(2).toString()));
            servletContext.setAttribute(list.get(0).toString(), food);
            for (int i = 0; i < Integer.parseInt((String) list.get(1)); i++) {
                foodStand.add(food);
            }
        }
        servletContext.setAttribute("foodStand", foodStand);
        servletContext.setAttribute("basket",  new ArrayList<>());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/init.jsp");
        requestDispatcher.forward(req, resp);
    }
}
