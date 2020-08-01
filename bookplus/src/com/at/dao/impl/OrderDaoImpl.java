package com.at.dao.impl;

import com.at.bean.Order;
import com.at.dao.OrderDao;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-27 16:34
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public Integer saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
