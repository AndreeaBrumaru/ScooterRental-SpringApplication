package com.practice.scooterrentalspringapplication.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//The CSP filter is used to restrict the usage of script (JavaScript), CSS files from particular sources;
//only allowing the same domain to load these script files.
@Component
@Order(5)
public class ContentSecurityPolicyFilter extends RequestContextFilter {

    private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOG.info("Content Security Policy Filter...");
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        setCacheHeaders(responseWrapper);

        responseWrapper.copyBodyToResponse();
    }

    private void setCacheHeaders(ContentCachingResponseWrapper responseWrapper)
    {
        responseWrapper.setHeader("Content-Security-Policy", "default-src 'self'");
        responseWrapper.setHeader("Content-Security-Policy", "script-src 'self'");
    }
}
