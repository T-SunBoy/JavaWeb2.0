package com.at.servlet;

import com.at.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-24 13:38
 */
public class CookieServlet extends BaseServlet {
    /**
     * Description:创建cookie对象
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 14:14 2020/7/24
     **/

    protected void creatCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建cookie对象
        Cookie cookie = new Cookie("key1", "value1");
        //通知客户端保存cookie对象的数据
        resp.addCookie(cookie);
        //回传数据给客户端
        /*PrintWriter writer = resp.getWriter();
        writer.write("Cookie对象被创建了!!!");*/
        resp.getWriter().write("Cookie对象被创建了!!!");
    }

    /**
     * Description:服务器获取cookie对象
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 14:15 2020/7/24
     **/

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie isWantCookie = CookieUtils.findCookie("key1", req.getCookies());
        if (isWantCookie != null) {
            //找到了想要的cookie
            resp.getWriter().write("Cookie[" + isWantCookie.getName() + "=" + isWantCookie.getValue() + "] <br/>");
            resp.getWriter().write("找到了想要的cookie");

        } else {
            resp.getWriter().write("没有找到了想要的cookie");
        }
    }

    /**
     * Description:修改cookie的value，不能包含中文，空格。。。许多特殊的值，设置base64编码解决
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 14:35 2020/7/24
     **/


    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Cookie cookie = new Cookie("key1", "newValue1");
        resp.addCookie(cookie);
        resp.getWriter().write("值已经修改好!!!!!");*/

        //方案2
        Cookie isWantCookie = CookieUtils.findCookie("key1", req.getCookies());
        isWantCookie.setValue("newValue2");
        resp.addCookie(isWantCookie);
        if (isWantCookie != null) {
            resp.getWriter().write("Cookie[" + isWantCookie.getName() + "=" + isWantCookie.getValue() + "] <br/>");
            resp.getWriter().write("找到了想要修改的cookie");
        } else {
            resp.getWriter().write("没有找到了修改想要的cookie");
        }
    }

    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("key5", "value5");
        cookie.setPath(req.getContextPath()+"/abc");
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个带有 Path 路径的 Cookie");

        Cookie cookie5 = CookieUtils.findCookie("key5", req.getCookies());
        String name = cookie.getName();
        String value = cookie.getValue();
        System.out.println("cookie5:   "+name + value);
    }
}
