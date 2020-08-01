package com.at.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * 描述:拦截访问后台的请求，让其必须登录才能访问
 * @date 2020-07-28 21:46
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
/**
 * Description:一旦访问了manager下的资源，就进行拦截判断，登陆后才能访问
 * @Author tao
 * @Date 21:57 2020/7/28
 * @param request
 * @param response
 * @param chain
 * @return void
 **/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取用户的登录信息，判断用户是否登录了账号-----HttpServletRequest是ServletRequest的子接口
        HttpServletRequest req = (HttpServletRequest) request;
        Object userLogin = req.getSession().getAttribute("userLogin");
        if(userLogin != null){
            //用户已经登录
            chain.doFilter(request,response);
        }else {
            //用户未登录，让其到登录页面去登录
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }

    }

    @Override
    public void destroy() {

    }
}
