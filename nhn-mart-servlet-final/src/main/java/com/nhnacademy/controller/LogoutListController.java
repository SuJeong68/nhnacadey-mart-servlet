package com.nhnacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutListController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (Objects.nonNull(session)) {
            session.invalidate();
        }
        return "redirect:/loginForm.jsp";
    }
}
