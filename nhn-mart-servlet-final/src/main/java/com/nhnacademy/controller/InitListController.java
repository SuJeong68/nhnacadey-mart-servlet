package com.nhnacademy.controller;

import com.nhnacademy.object.Food;
import com.nhnacademy.object.FoodStand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InitListController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();

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

        return "/init.jsp";
    }
}
