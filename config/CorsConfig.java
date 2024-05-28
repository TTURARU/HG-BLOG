package com.hg.hgblogback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // WebMvcConfigurer 인터페이스의 addCorsMappings 메서드 재정의
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO Auto-generated method stub
        registry.addMapping("/**")
        .allowedMethods("*")
        .allowedOrigins("*");
    } //* addCorsMappings */
} //* CorsConfig */
