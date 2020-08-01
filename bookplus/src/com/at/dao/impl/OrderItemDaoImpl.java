package com.at.dao.impl;

import com.at.bean.OrderItem;
import com.at.dao.OrderItemDao;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-27 16:34
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public Integer saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`price`,`total_money`,`count`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getCount(), orderItem.getOrderId());
    }
}
