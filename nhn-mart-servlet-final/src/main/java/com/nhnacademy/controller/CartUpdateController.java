package com.nhnacademy.controller;

import com.nhnacademy.object.Food;
import com.nhnacademy.object.FoodStand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartUpdateController implements Command {
    private List<Food> basket;
    private String[] foodnames = {"onion", "egg", "green-onion", "apple"};

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletContext servletContext = request.getServletContext();
            FoodStand foodStand = (FoodStand) servletContext.getAttribute("foodStand");
            List<Food> foodstandArr = List.class.cast(foodStand.getFoods().clone());

            String[] reqFoods = request.getParameterValues("food");
            if (Arrays.stream(reqFoods).allMatch(s -> s.equals(""))) {
                return "redirect:/foods.jsp";
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
                            return "/error.jsp";
                        }
                    }
                    basket.add(foodstandArr.get(index));
                    foodstandArr.remove(index);
                }
            }
            servletContext.setAttribute("basket", basket);

            foodStand.setFoods((ArrayList<Food>) foodstandArr);
            servletContext.setAttribute("foodStand", foodStand);

            return "redirect:/cart.jsp";
        } catch (NullPointerException e) {
            return "/error.jsp";
        }
    }
}