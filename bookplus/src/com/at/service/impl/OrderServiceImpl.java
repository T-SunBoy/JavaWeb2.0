package com.at.service.impl;

import com.at.bean.*;
import com.at.dao.BookDao;
import com.at.dao.OrderDao;
import com.at.dao.OrderItemDao;
import com.at.dao.impl.BookDaoImpl;
import com.at.dao.impl.OrderDaoImpl;
import com.at.dao.impl.OrderItemDaoImpl;
import com.at.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-27 17:20
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    /**
     * Description:创建订单，并保存到数据库
     *
     * @param cart   购物车对象
     * @param userId 创建订单的用户
     * @return java.lang.String 返回订单号
     * @Author tao
     * @Date 21:41 2020/7/27
     **/

    @Override
    public String CreatOrder(Cart cart, Integer userId) {
        //唯一订单id创建
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单至数据库订单表中（t_order）
        orderDao.saveOrder(order);
        //模拟异常，检验使用事务性原理后，是否能保证数据的安全
        //int a = 12 / 0;
        //遍历购物车中每一个商品项转换为订单项,并保存到数据库订单项的表（t_order_item）中
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取商品项中的每一个商品数据
            CartItem cartItem = entry.getValue();
            //转换为订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);


            //更新书的库存和销量
            Book book = bookDao.queryBookById(entry.getValue().getId());
            System.out.println("修改前的book数据： " + book);
            book.setSales(book.getSales() + entry.getValue().getCount());
            book.setStock(book.getStock() - entry.getValue().getCount());
            System.out.println("修改后的book数据： " + book);
            //将更新的数据保存至数据库
            bookDao.updateBook(book);
        }

        //创建订单成功后，清空购物车
        cart.clear();

        return orderId;
    }
}
