package com.assessments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import config.SwaggerConfiguration;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class AssessmentsApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(AssessmentsApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }

}
