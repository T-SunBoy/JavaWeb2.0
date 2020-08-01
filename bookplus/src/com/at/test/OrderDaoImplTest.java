package com.at.test;

import com.at.bean.Order;
import com.at.bean.OrderItem;
import com.at.dao.OrderDao;
import com.at.dao.OrderItemDao;
import com.at.dao.impl.OrderDaoImpl;
import com.at.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-27 16:52
 */
public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("12345001",new Date(),new BigDecimal(1000),0,1 ));

    }

    @Test
    public void saveOrderItem() {
        OrderItemDao orderDao = new OrderItemDaoImpl();
        orderDao.saveOrderItem(new OrderItem(null,"你我",1,new BigDecimal(102),new BigDecimal(102),"12345001"));
        orderDao.saveOrderItem(new OrderItem(null,"你",1,new BigDecimal(103),new BigDecimal(103),"12345001"));

    }


}