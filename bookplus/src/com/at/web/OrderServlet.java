package com.at.web;

import com.at.bean.Cart;
import com.at.bean.User;
import com.at.service.OrderService;
import com.at.service.impl.OrderServiceImpl;
import com.at.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * 描述:订单的servlet程序
 * @date 2020-07-27 17:56
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * Description:点击结账，生成订单，并将订单、订单项的数据保存到数据库中
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 17:57 2020/7/27
     **/
    protected void creatOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过session获取购物车的数据
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取用户登录信息
        User userLogin = (User) req.getSession().getAttribute("userLogin");
        if (userLogin == null) {
            //让用户到登录页面进行登陆,再去结算购物车
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userLoginId = userLogin.getId();
        //通过orderService.CreatOrder(Cart cart, Integer userId)生成订单，并保存订单项到数据库
        String orderId = orderService.CreatOrder(cart, userLoginId);

        System.out.println("订单号是：" + orderId);

        //将订单号发送给结算页面，显示
        req.getSession().setAttribute("orderId", orderId);

        //重定向到结算页面
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
