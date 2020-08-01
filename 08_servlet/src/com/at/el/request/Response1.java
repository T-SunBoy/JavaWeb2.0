package com.at.el.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-12 17:10
 * @description
 */
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //重定向的使用
        System.out.println("访问新的地址!!!!!");
        //1.设置响应码：302表示重定向
        resp.setStatus(302);
        //2.设置响应头说明重定向的地址
        resp.setHeader("Location", "http://localhost:8080/08_servlet/response2");


        //使用重定向的第二种方式(推荐使用)
        resp.sendRedirect("http://localhost:8080/08_servlet/response2");

    }
}
