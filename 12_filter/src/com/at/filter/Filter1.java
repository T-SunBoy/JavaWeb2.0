package com.at.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-28 18:59
 */
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter1:前置代码");
        chain.doFilter(request, response);
        System.out.println("filter1:后置代码");

    }

    @Override
    public void destroy() {

    }
}
