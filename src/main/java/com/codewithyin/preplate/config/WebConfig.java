package com.codewithyin.preplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir:menu-images}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Convert to absolute path
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        String uploadLocation = "file:///" + uploadPath.toString().replace("\\", "/") + "/";

        System.out.println("Serving uploaded images from: " + uploadLocation);

        // Map URL path to file system path
        registry.addResourceHandler("/menu-images/**")
                .addResourceLocations(uploadLocation);
    }
}