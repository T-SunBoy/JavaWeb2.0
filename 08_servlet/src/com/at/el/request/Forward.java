package com.at.el.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-12 15:03
 * @description
 */
public class Forward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("请求转发：");
        req.getRequestDispatcher("/a/b/c.html").forward(req, resp);

    }
}
