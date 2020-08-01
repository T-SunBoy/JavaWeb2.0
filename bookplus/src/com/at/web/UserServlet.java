package com.at.web;

import com.at.bean.User;
import com.at.service.UserService;
import com.at.service.impl.UserServiceImpl;
import com.at.utils.WebBeanUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author tao
 * @version 2.0
 * 描述:BaseServlet重写了doPost()方法，让本类继承BaseServlet
 * <p>
 * 1.利用隐藏域，合并登录和注册俩个功能，让一个模块只有一个servlet程序
 * 2.利用反射解决大量ifelse的判断操作
 * 3.使用BeanUtils:解决获取参数,给对象注入属性的繁杂问题
 * @date 2020-07-19 11:17
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * Description:处理登录功能
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 12:32 2020/7/19
     **/
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("处理登录功能");
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //封装用户得登录信息，此时只有账号密码俩个信息
        User userLogin = WebBeanUtils.CopyParameter(req.getParameterMap(), new User());

        //2.查询数据，开始登录,并返回用户所有信息
        userLogin = userService.login(userLogin);
        //3.验证账号和密码
        if (userLogin != null) {
            //用户登录成功后将用户信息信息保存在session中
            req.getSession().setAttribute("userLogin", userLogin);

            //跳到登录成功的页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);

        } else {
            //处理登录失败的逻辑功能
            req.setAttribute("msg", "账户名或密码错误");
            if (username == "" || password == "") {
                req.setAttribute("msg", "请输入用户名或密码");
            }
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * Description:注销用户登录信息
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 12:14 2020/7/25
     **/

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
        System.out.println("getContextPath():  " + req.getContextPath());
    }

    /**
     * Description:处理注册功能
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 12:33 2020/7/19
     **/

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("处理注册功能");


        //获取随机产生的验证码
        String kaptchaCode = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //获取后立即销毁，防止用户重复提交
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        //将获取的用户注册信息封装到对应的bean对象中
        User user = WebBeanUtils.CopyParameter(req.getParameterMap(), new User());

        //  2、检查 验证码是否正确  === 写死,暂时要求验证码为:abcde
        if (kaptchaCode != null && kaptchaCode.equalsIgnoreCase(code)) {
            // 3、检查 用户名是否可用
            if (userService.existsUsername(username)) {

                System.out.println("用户名[" + username + "]已存在!");
                //错误提示
                req.setAttribute("msg", "用户名已经存在");

                //错误，返回页面时，需要在表单项保留的信息(只考虑保留账户和邮箱)
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //调用Service保存到数据库
                // userService.registUser(new User(null, username, password, email));
                //当对象属性过多时，使用beanutils注入对象属性，更加简洁
                userService.registUser(user);

                //跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            System.out.println("验证码[" + code + "]错误");
            //错误提示
            req.setAttribute("msg", "验证码错误");
            //错误，返回页面时，需要在表单项保留的信息(只考虑保留账户和邮箱)
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(req.getParameter("username"));
        Gson gson = new Gson();
        Map<String, Object> mapJson = new HashMap<>();
        mapJson.put("existsUsername", existsUsername);
        String mapJsonString = gson.toJson(mapJson);
        resp.getWriter().write(mapJsonString);
    }
}
