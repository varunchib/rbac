package com.venture.rbac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import jakarta.servlet.http.HttpSessionListener;

@Configuration
public class SecurityConfig {

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(jakarta.servlet.http.HttpSessionEvent se) {
                se.getSession().setMaxInactiveInterval(30 * 60); // 30 minutes
            }
        };
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}