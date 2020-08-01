package com.at.dao;

import com.at.bean.OrderItem;

/**
 * @author tao
 * @version 1.0
 * 描述:订单项的管理操作
 * @date 2020-07-27 16:27
 */
public interface OrderItemDao {
    /**
     * Description:将订单项的商品数据保存到数据库，每个订单项对应着一个商品
     *
     * @param orderItem 要保存的订单项商品
     * @return java.lang.Integer 返回影响的行数，-1表示失败
     * @Author tao
     * @Date 21:36 2020/7/27
     **/

    public Integer saveOrderItem(OrderItem orderItem);
}
