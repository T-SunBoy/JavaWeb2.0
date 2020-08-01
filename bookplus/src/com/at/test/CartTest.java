package com.at.test;

import com.at.bean.Cart;
import com.at.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-26 14:23
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(2,"java的编程思想",1,new BigDecimal(29),new BigDecimal(29)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(2,"java的编程思想",1,new BigDecimal(29),new BigDecimal(29)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(2,"java的编程思想",1,new BigDecimal(29),new BigDecimal(29)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(19),new BigDecimal(19)));
        cart.addItem(new CartItem(2,"java的编程思想",1,new BigDecimal(29),new BigDecimal(29)));
        cart.updateCount(1, 5);
        System.out.println(cart);
    }
}