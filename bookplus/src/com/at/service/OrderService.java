package com.at.service;

import com.at.bean.Cart;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-27 17:16
 */
public interface OrderService {
    /**
     * Description:生成订单
     * @Author tao
     * @Date 17:18 2020/7/27
     * @param cart 生成订单时需要的购物车数据
     * @param userId 用户id
     * @return java.lang.String 返回订单号
     **/


    public String CreatOrder(Cart cart,Integer userId);
}
