package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;

@Slf4j
@WebServlet(name = "loginServlet", urlPatterns = "/login",  initParams = {
        @WebInitParam(name = "id", value = "sujeong"),
        @WebInitParam(name = "pwd", value = "1234")
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initParamId = getServletConfig().getInitParameter("id");
        String initParamPwd = getServletConfig().getInitParameter("pwd");

        String reqId = req.getParameter("id");
        String reqPwd = req.getParameter("pwd");

        if (initParamId.equals(reqId) && initParamPwd.equals(reqPwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", reqId);

            Iterator attributeIt = getServletContext().getAttributeNames().asIterator();
            while (attributeIt.hasNext()) {
                String s = (String) attributeIt.next();
                if (s.equals(reqId + "balance")) {
                    resp.sendRedirect("/login");
                    return;
                }
            }
            getServletContext().setAttribute(reqId + "balance", new BigDecimal(20000));

            String lang = getServletContext().getInitParameter("lang");
            getServletContext().setAttribute("lang", lang);

            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/loginForm.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Login</title></head><body>");

        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)) {
            resp.sendRedirect("/loginForm.html");
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
