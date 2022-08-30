package com.practice.scooterrentalspringapplication.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.scooterrentalspringapplication.filter.wrapper.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class RequestPostJsonBodyFilter  implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter: {}", this);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper wrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        if(wrapper.getMethod().equals("POST"))
        {
            byte[] body = StreamUtils.copyToByteArray(wrapper.getInputStream());
            Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);
            filterChain.doFilter(wrapper, servletResponse);
            LOG.info("Logging response: {}", jsonRequest);
        }
        else
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter: {}", this);
    }
}
