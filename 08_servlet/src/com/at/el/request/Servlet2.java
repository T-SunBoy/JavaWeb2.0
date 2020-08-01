package com.at.el.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-12 14:02
 * @description
 */
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");
        System.out.println(username);
        //检查servlet1是否给参数盖章
        Object key1 = req.getAttribute("key1");
        System.out.println("是否有盖章:"+ key1);
        System.out.println("servlet2处理自己的事务");


    }
}
