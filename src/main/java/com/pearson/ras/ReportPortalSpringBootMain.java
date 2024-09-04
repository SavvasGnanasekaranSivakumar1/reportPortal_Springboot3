package com.pearson.ras;

import com.pearson.ras.config.MyWebApplicationContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.pearson.ras" })
public class ReportPortalSpringBootMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ReportPortalSpringBootMain.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        WebApplicationContext context = new MyWebApplicationContext();

        addSpringDispatcherServlet(servletContext, context);

        System.out.println("context intialization over");
    }
    private void addSpringDispatcherServlet(ServletContext servletContext, WebApplicationContext context) {
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
                new DispatcherServlet(context));
        System.out.println("context 2 over dispatcher-*********************************--"+dispatcher);
        dispatcher.setLoadOnStartup(1);
        //dispatcher.addMapping("*.do");
        dispatcher.addMapping("*.do","/","*.html");
        dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        System.out.println("context 2 over");
    }
    public static void main(String[] args) {
        SpringApplication.run(new Class[] {ReportPortalSpringBootMain.class},args);
    }
}
