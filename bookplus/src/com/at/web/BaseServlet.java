package com.at.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author tao
 * @version 1.0
 * 描述:问题：当功能繁多时（登录、注册，注销、修改账户、修改密码。。。。），此时产生大量ifelse语句判断.
 * 解决办法：利用反射，根据在隐藏域中获取的不同请求参数，决定掉用什么方法（注册，登录等等。。。）
 * 1.继承HttpServlet，重写doPost()方法，根据请求参数调用不同的功能的方法
 * 2.让web层的UserServlet继承此类
 * @date 2020-07-19 14:47
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求为get请求并出错时，转到post请求去处理问题
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post请求中文乱码问题
        // 一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("UTF-8");
        //解决响应中文乱码的问题
        resp.setContentType("text/html; charset=UTF-8");

        //获取隐藏域中请求参数的值，根据是logon、register、或其它，来处理不同的逻辑业务;
        String action = req.getParameter("action");
        try {
            //获取需要调用的方法
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            System.out.println(action + "方法被调用了：" + declaredMethod);
            //调用相对应的方法，处理业务
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //老办法：过于繁琐
        //System.out.println(action);
        /*if ("login".equals(action)) {
            //System.out.println("处理登录功能");
            login(req, resp);
        } else if ("register".equals(action)) {
            //System.out.println("处理注册功能");
            regist(req, resp);
        }*/
    }

}
