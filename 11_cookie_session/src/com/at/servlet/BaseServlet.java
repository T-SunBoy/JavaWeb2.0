package com.at.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * 根据获取去请求参数，动态的调用对应方法名的方法
 * @date 2020-07-24 12:49
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求中文乱码的问题
        req.setCharacterEncoding("utf-8");
        //解决响应中文乱码的问题
        resp.setContentType("text/html; charset=UTF-8");


        //使用反射动态获取请求参数，决定调用什么方法
        String action = req.getParameter("action");
        System.out.println(action);
        try {
            //在servlet程序中写的方法，都会使用HttpServletRequest req, HttpServletResponse resp这俩个参数
            //所以使用反射获取方法时，传入方法的类型参数的class，和方法名
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用对应字符串action的方法，并传入，调用该方法的对象（即为this当前对象），和方法参数
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
