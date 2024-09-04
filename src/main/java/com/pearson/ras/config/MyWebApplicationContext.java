package com.pearson.ras.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyWebApplicationContext extends AnnotationConfigWebApplicationContext {
    private static final String CONFIG_FILES_LOCATION = "com.pearson.ras";

    public MyWebApplicationContext() {
        super();
        setConfigLocation(CONFIG_FILES_LOCATION);
    }

}