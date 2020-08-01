package com.at.servlet;

import com.at.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author tao
 * @version 1.0
 * 描述:使用cookie，模拟免用户名登陆
 * @date 2020-07-24 16:08
 */
public class LoginServlet extends BaseServlet {

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取验证码
        String attribute = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println("attribute"+attribute);
        //销毁验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

       String code = (String) req.getParameter("code");
        System.out.println("code"+code);
        if(code.equals(attribute)){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if ("jitao".equals(username) && "123456".equals(password)) {
                System.out.println("开始登录");
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 60 * 24);
                //cookie.setPath(req.getContextPath()+"/abc");
                resp.addCookie(cookie);
                req.getRequestDispatcher("login_success.jsp").forward(req, resp);
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }
        }else {
            System.out.println("请不要重复提交");
        }

    }
}
