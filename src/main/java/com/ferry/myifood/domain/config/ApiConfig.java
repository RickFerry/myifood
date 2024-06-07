package com.ferry.myifood.domain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    FilterRegistrationBean<SquigglyRequestFilter> filterRegistrationBean(ObjectMapper objectMapper) {
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider("fields", null));
        FilterRegistrationBean<SquigglyRequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new SquigglyRequestFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
