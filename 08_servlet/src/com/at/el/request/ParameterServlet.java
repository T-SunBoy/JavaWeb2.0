package com.at.el.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-12 12:39
 * @description
 */
public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //String hobby = req.getParameter("hobby");
        String[] hobbies = req.getParameterValues("hobby"); //多个爱好时
        //String s = Arrays.toString(hobbies);
        System.out.println("用户名："+username+" 密码："+password+" hobby: "+ Arrays.toString(hobbies));

    }
}
