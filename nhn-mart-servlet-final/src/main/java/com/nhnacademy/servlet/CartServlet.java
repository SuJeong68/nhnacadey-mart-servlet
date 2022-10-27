package com.nhnacademy.servlet;

import com.nhnacademy.controller.Command;
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
import java.util.*;

@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    private List<Food> basket;
    private String[] foodnames = {"onion", "egg", "green-onion", "apple"};

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        try {
            ServletContext servletContext = getServletConfig().getServletContext();
            FoodStand foodStand = (FoodStand) servletContext.getAttribute("foodStand");
            List<Food> foodstandArr = List.class.cast(foodStand.getFoods().clone());

            String[] reqFoods = req.getParameterValues("food");
            if (Arrays.stream(reqFoods).allMatch(s -> s.equals(""))) {
                resp.sendRedirect("/foods");
                return;
            }

            int[] count = new int[reqFoods.length];
            for (int i = 0; i < reqFoods.length; i++) {
                if (reqFoods[i].equals(""))
                    continue;
                count[i] = Integer.parseInt(reqFoods[i]);
            }

            basket = (List<Food>) servletContext.getAttribute("basket");

            for (int i = 0; i < reqFoods.length; i++) {
                for (int j = 0; j < count[i]; j++) {
                    int index = 0;
                    while (!foodstandArr.get(index).getName().equals(foodnames[i])) {
                        index++;
                        if (index >= foodstandArr.size()) {
                            for (int x = 0; x < j; x++) {
                                basket.remove(basket.size() - 1);
                            }
                            resp.sendRedirect("/AmountException.jsp");
                            return;
                        }
                    }
                    basket.add(foodstandArr.get(index));
                    foodstandArr.remove(index);
                }
            }
            servletContext.setAttribute("basket", basket);

            foodStand.setFoods((ArrayList<Food>) foodstandArr);
            servletContext.setAttribute("foodStand", foodStand);

            resp.sendRedirect("/cart");
        } catch (NullPointerException e) {
            resp.sendRedirect("/init");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/cart.jsp");
        requestDispatcher.forward(req, resp);
    }
}

