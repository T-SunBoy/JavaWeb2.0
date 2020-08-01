package com.at.el.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-12 10:45
 * @description
 */
public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*
        i.getRequestURI() 获取请求的资源路径
        ii.getRequestURL() 获取请求的统一资源定位符（绝对路径）
        iii.getRemoteHost() 获取客户端的 ip 地址
        iv.getHeader()获取请求头
        v.getParameter() 获取请求的参数
        vi.getParameterValues() 获取请求的参数（多个值的时候使用）
        vii.getMethod() 获取请求的方式 GET 或 POST
        viii.setAttribute(key, value);
        设置域数据 ix.getAttribute(key);
        获取域数据 x.getRequestDispatcher() 获取请求转发对象
        */

        // i.getRequestURI() 获取请求的资源路径
        System.out.println("URI =>" + req.getRequestURI());
        //ii.getRequestURL() 获取请求的统一资源定位符（绝对路径）
        System.out.println("URL =>" + req.getRequestURL());
        //iii.getRemoteHost() 获取客户端的 ip 地址
        System.out.println("IP =>" + req.getRemoteHost());
        //iv.getHeader()获取请求头
        System.out.println("请求头User-Agent =>" + req.getHeader("User-Agent"));
        // v.getParameter() 获取请求的参数
        System.out.println("获取请求的参数(post/get) =>" + req.getMethod());


    }
}
