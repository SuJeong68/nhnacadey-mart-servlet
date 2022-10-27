package com.nhnacademy.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "excluded-urls", value = "/loginForm.jsp /login.jsp")
})
public class LoginCheckFilter implements Filter {
    private Set<String> excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("excluded-urls");

        excludedUrls = Arrays.stream(urls.split(" "))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (Objects.isNull(session)) {
            if (excludedUrls.contains(((HttpServletRequest)request).getRequestURI())) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/loginForm.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
