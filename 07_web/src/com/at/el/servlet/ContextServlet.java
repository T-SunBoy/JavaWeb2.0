package com.at.el.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-11 16:23
 * @description ServletContext 类的四个作用
 * 1、获取 web.xml 中配置的上下文参数 context-param
 * 2、获取当前的工程路径，格式: /工程路径
 * 3、获取工程部署后在服务器硬盘上的绝对路径
 * 4、像 Map 一样存取数据
 */
public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext类型的对象
        //1.方式一（推荐）
        ServletContext context = getServletContext();
        //2.方式二
        //ServletContext context1 = getServletConfig().getServletContext();

        //获取context-param参数的数据 :getAttribute()根据key值获取value，getInitParameter根据name值获取value
        //getParameter获取页面请求的参数
        String username = context.getInitParameter("username");

        String password = context.getInitParameter("password");
        System.out.println("username: " + username + " password:" + password);
        //Object username1 = context.getAttribute("username");
        //System.out.println("Attribute: "+ username1);
        //通过像map一样的形式存取数据
        context.setAttribute("keyOne", "valueOne");
        Object keyOne = context.getAttribute("keyOne");
        System.out.println(keyOne);

        //获取工程的相对
        String contextPath = context.getContextPath(); //相对路径
        System.out.println(contextPath);
        //绝对路径
        //  / 斜杠被服务器解析地址为:http://ip:port/工程名/ 映射到 IDEA 代码的 web 目录
        String realPath = context.getRealPath("/");
        System.out.println(realPath);
    }
}
