package com.at.dao;

import com.at.bean.Order;

/**
 * @author tao
 * @version 1.0
 * 描述:订单的管理操作
 * @date 2020-07-27 16:27
 */
public interface OrderDao {

    /**
     * Description:生成订单，将其保存至数据库
     *
     * @param order 需要保存的订单
     * @return java.lang.Integer 返回影响数据库的行数，-1表示失败
     * @Author tao
     * @Date 21:40 2020/7/27
     **/

    public Integer saveOrder(Order order);


}
