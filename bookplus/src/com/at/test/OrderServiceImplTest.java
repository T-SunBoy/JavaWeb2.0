package com.at.test;

import com.at.bean.Cart;
import com.at.bean.CartItem;
import com.at.service.OrderService;
import com.at.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-27 17:41
 */
public class OrderServiceImplTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void creatOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(2,"java的编程思想",1,new BigDecimal(29),new BigDecimal(29)));
        System.out.println(cart);
        String s = orderService.CreatOrder(cart, 1);
        System.out.println("订单号:"+s);

    }
}