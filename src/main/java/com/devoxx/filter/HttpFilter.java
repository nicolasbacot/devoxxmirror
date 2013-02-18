package com.devoxx.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public abstract class HttpFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {
        config = null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (!(req instanceof HttpServletRequest)) {
            throw new ServletException("This filter can only process HttpServletRequest requests");
        }
        doHttpFilter((HttpServletRequest) req, resp, chain);
    }

    protected abstract void doHttpFilter(HttpServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException;
}