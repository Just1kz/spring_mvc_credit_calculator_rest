package com.antonbelykh.spring.spring_mvc.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//поднятие контекста - контейнер бинов
@Configuration
@ComponentScan(basePackages = "com.antonbelykh.spring.spring_mvc.rest")
@EnableWebMvc
@EnableTransactionManagement
public class MyAppContextConfig {
}
