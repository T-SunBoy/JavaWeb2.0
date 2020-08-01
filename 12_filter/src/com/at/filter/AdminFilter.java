package com.at.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-28 13:21
 */
public class AdminFilter implements Filter {

    public AdminFilter() {
        System.out.println("1、filter的构造器方法");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("2、filter的init初始化方法");

        //FilterConfig 类的作用是获取 filter 过滤器的配置内容
        // 1、获取 Filter 的名称 filter-name 的内容
        System.out.println("Filter 的名称 :"+filterConfig.getFilterName());
        // 2、获取在 Filter 中配置的 init-param 初始化参数
        System.out.println("Filter 中配置的 init-param 初始化参数:"+filterConfig.getInitParameter("username"));
        // 3、获取 ServletContext 对象
        System.out.println("获取 ServletContext 对象:"+filterConfig.getServletContext());

    }

    /**
     * Description:doFilter 方法，专门用于拦截请求,过滤请求。可以做权限检查
     *
     * @param request
     * @param response
     * @param chain
     * @return void
     * @Author tao
     * @Date 13:08 2020/7/28
     **/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("3、filter的doFilter过滤器方法");

        HttpServletRequest req = (HttpServletRequest) request;
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // 让程序继续往下访问用户的目标资源，没有此行无法访问指定的资源
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("4、filter的销毁过滤器的方法");

    }
}
