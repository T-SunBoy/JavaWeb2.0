package com.at.el.servlet;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-11 10:47
 * @description ServletConfig 类的三大作用
 * 1、可以获取 Servlet 程序的别名 servlet-name 的值
 * 2、获取初始化参数 init-param
 * 3、获取 ServletContext 对象
 */
public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //获取servlet程序的别名
        String servletName = servletConfig.getServletName();
        System.out.println(servletName);
        String username = servletConfig.getInitParameter("username");

        System.out.println(username);
        String url = servletConfig.getInitParameter("url");
        System.out.println(url);

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Hello servlet被访问了!!!!!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
