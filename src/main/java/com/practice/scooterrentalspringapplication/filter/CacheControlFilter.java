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

//The Cache-Control HTTP header field holds directives (instructions) — in both requests and responses —
// that control caching in browsers and shared caches (e.g. Proxies, CDNs).
@Component
@Order(6)
public class CacheControlFilter extends RequestContextFilter {

    private final static Logger LOG = LoggerFactory.getLogger(CacheControlFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOG.info("Cache Filter...");

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        setCacheHeaders(responseWrapper);
        responseWrapper.copyBodyToResponse();
    }

    private void setCacheHeaders(ContentCachingResponseWrapper responseWrapper) {
        responseWrapper.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        responseWrapper.setHeader("Pragma", "no-cache"); // HTTP 1.0
        responseWrapper.setHeader("Expires", "0");
    }
}
