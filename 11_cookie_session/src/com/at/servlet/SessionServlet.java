package com.at.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-24 19:21
 */
public class SessionServlet extends BaseServlet {

    protected void creatSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取一个session
        HttpSession session = req.getSession();
        System.out.println("Session id:" + session.getId());
        System.out.println("Session isNew?:" + session.isNew());
        resp.getWriter().write("得到了session<br/>" + "Session id:" + session.getId() + "<br/>" + "Session isNew?:" + session.isNew());


    }

    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("key1", "value1");
        boolean aNew = session.isNew();
        String id = session.getId();
        resp.getWriter().write("已经向session中设置了数据" + "<br/>   anew:" + aNew + "    <br/>id:" + id);

    }

    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute("key1");
        boolean aNew = req.getSession().isNew();
        String id = req.getSession().getId();
        resp.getWriter().write("获取到了数据" + (String) attribute + "<br/>  anew:" + aNew + "    <br/>id:" + id);

    }

    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();
        resp.getWriter().write("Session会话的默认超时时长为：" + maxInactiveInterval);
    }
    protected void destroy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setMaxInactiveInterval(3);
        resp.getWriter().write("Session会话超时三秒销毁");
        //让会话立即销毁
        req.getSession().invalidate();
    }
    protected void destroyNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //让会话立即销毁
        req.getSession().invalidate();
        resp.getWriter().write("Session会话立即销毁");
    }
}
