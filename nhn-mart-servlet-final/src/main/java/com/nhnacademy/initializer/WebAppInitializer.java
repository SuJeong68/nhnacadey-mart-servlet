package com.nhnacademy.initializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes({
        javax.servlet.http.HttpServlet.class,
        javax.servlet.Filter.class,
        javax.servlet.ServletContextListener.class,
        javax.servlet.http.HttpSessionListener.class
})
public class WebAppInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ctx.setInitParameter("foodlist",
                "onion, 2, 1000\n" +
                "egg, 5, 2000\n" +
                "green-onion, 10, 500\n" +
                "apple, 20, 2000");
        ctx.setInitParameter("lang", "ko");
    }
}
