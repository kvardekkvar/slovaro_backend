package com.example.slovaro_backend;

import com.example.slovaro_backend.entity.Source;
import com.example.slovaro_backend.entity.User;
import com.example.slovaro_backend.entity.Word;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@ComponentScan
public class Config {

    @Bean(name = "entityManagerFactory")
    SessionFactory sessionFactory(Environment env){
        String password = env.getProperty("hibernate.connection.password");
        String user = env.getProperty("hibernate.connection.user");

        assert user != null;
        assert password != null;

        return new org.hibernate.cfg.Configuration()
                .setProperty("hibernate.connection.user", user)
                .setProperty("hibernate.connection.password", password)
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Source.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Word.class)
                .buildSessionFactory();

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
            }
        };
    }





}
