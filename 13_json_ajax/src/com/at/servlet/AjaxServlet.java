package com.at.servlet;

import com.at.bean.Person;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-30 14:53
 */
public class AjaxServlet extends BaseServlet {
    protected void ajaxRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到了ajax的请求");
        Person person = new Person(1, "是你吗?");
        Gson gson = new Gson();
        //将person对象转为json字符串，发给客户端，转换的字符串类似于map集合的键值关系
        String personJson = gson.toJson(person);
        System.out.println(personJson);
        resp.getWriter().write(personJson);
    }

    protected void ajaxGetJson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("username:  "+req.getParameter("username"));
        System.out.println("password:  "+req.getParameter("password"));
        System.out.println("收到了ajaxGetJson的请求");

        Person person = new Person(1, "是你吗?");
        Gson gson = new Gson();
        //将person对象转为json字符串，发给客户端，转换的字符串类似于map集合的键值关系
        String personJson = gson.toJson(person);
        System.out.println(personJson);
        resp.getWriter().write(personJson);
    }
}
