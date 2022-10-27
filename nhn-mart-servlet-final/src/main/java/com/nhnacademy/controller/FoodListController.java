package com.nhnacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FoodListController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/foods.jsp";
    }
}
