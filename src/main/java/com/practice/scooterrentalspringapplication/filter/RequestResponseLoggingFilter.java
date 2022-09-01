package com.practice.scooterrentalspringapplication.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestResponseLoggingFilter implements Filter {
    private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        LOG.info("Logging request {} : {}", req.getMethod(), req.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
        LOG.info("Logging response: {}", res.getContentType());
    }

    //@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter: {}", this);
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter: {}", this);
    }
}
