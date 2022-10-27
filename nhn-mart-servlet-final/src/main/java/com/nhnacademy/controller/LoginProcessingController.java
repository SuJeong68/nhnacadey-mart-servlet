package com.nhnacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Iterator;

public class LoginProcessingController implements Command {

    private String id;
    private String pwd;

    public LoginProcessingController(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String reqId = req.getParameter("id");
        String reqPwd = req.getParameter("pwd");

        if (this.id.equals(reqId) && this.pwd.equals(reqPwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", reqId);

            Iterator attributeIt = req.getServletContext().getAttributeNames().asIterator();
            while (attributeIt.hasNext()) {
                String s = (String) attributeIt.next();
                if (s.equals(reqId + "balance")) {
                    return "redirect:/login.jsp";
                }
            }
            req.getServletContext().setAttribute(reqId + "balance", new BigDecimal(20000));

            String lang = req.getServletContext().getInitParameter("lang");
            req.getServletContext().setAttribute("lang", lang);

            return "redirect:/login.jsp";
        } else {
            return "redirect:/loginForm.jsp";
        }
    }
}
