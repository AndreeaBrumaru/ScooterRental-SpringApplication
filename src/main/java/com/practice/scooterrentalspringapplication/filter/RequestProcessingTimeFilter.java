package com.practice.scooterrentalspringapplication.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(3)
public class RequestProcessingTimeFilter extends GenericFilterBean {
    private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long time = System.currentTimeMillis();
        try
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally
        {
            time = System.currentTimeMillis() - time;
            LOG.info("Request '{}'  was processed in: {} ms", ((HttpServletRequest) servletRequest).getRequestURI(), time);
        }

    }
}
