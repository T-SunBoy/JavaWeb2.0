package com.at.el.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-12 16:05
 * @description
 */
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决相应流的编码问题：中文
        //设置服务器和客户端都使用utf-8的字符编码：必须在获取响应流之前设置
        resp.setContentType("text/html; charset=utf-8");
        //查看字符集
        System.out.println(resp.getCharacterEncoding());

        //往客户端回传数据
        PrintWriter writer = resp.getWriter();
        writer.write("崩撤卖溜");
    }
}
