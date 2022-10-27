package com.nhnacademy.servlet;

import com.nhnacademy.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            Command command = resolveServlet(req.getServletPath(), req.getMethod());

            String view = command.execute(req, resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("exception", e);

            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private Command resolveServlet(String servletPath, String method) {
        Command command = null;

        if ("/cart.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new CartListController();
        } else if ("/cart.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new CartUpdateController();
        } else if ("/foods.do".equals(servletPath)) {
            command = new FoodListController();
        } else if ("/login.do".equals(servletPath)) {
            command = new LoginProcessingController("sujeong", "3333");
        } else if ("/loginForm.do".equals(servletPath)) {
            command = new LoginFormController();
        } else if ("/logout.do".equals(servletPath)) {
            command = new LogoutListController();
        } else if ("/changeLang.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new ChangeLangListController();
        } else if ("/changeLang.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new ChangeLangUpdateController();
        } else if ("/init.do".equals(servletPath)) {
            command = new InitListController();
        } else if ("/pay.do".equals(servletPath)) {
            command = new PayUpdateController();
        }

        return command;
    }
}
