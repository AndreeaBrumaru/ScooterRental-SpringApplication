package com.practice.scooterrentalspringapplication.filter;

import com.practice.scooterrentalspringapplication.filter.RequestPostJsonBodyFilter;
import com.practice.scooterrentalspringapplication.filter.RequestResponseLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter()
    {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());

        registrationBean.addUrlPatterns("/admin/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RequestPostJsonBodyFilter> jsonBodyFilter()
    {
        FilterRegistrationBean<RequestPostJsonBodyFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestPostJsonBodyFilter());

        registrationBean.addUrlPatterns("/admin/users");
        registrationBean.setOrder(4);

        return registrationBean;
    }
}
