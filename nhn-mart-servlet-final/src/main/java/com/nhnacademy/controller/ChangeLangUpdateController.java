package com.nhnacademy.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLangUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter("lang");
        req.getServletContext().setAttribute("lang", lang);

        return "/index.jsp";
    }
}

